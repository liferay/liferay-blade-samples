package com.liferay.blade.tests

import aQute.bnd.osgi.Jar;
import aQute.bnd.osgi.Processor;
import aQute.bnd.deployer.repository.FixedIndexedRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import org.gradle.api.GradleException;
import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification

class BladeTests extends Specification {
	def bladeJarPath

	def executeBlade(String... args) {
		def bladeclijar = getLatestBladeCLIJar()
		def cmdline = "java -jar ${bladeclijar} ${args.join(' ')}"
		return cmdline.execute()
	}

	def getLatestBladeCLIJar() {
		if (bladeJarPath == null) {
			def repoPath = new File("build").absolutePath
			def repo = new FixedIndexedRepo()

			repo.setProperties([
				"name" : "index1",
				"locations" : "https://liferay-test-01.ci.cloudbees.com/job/liferay-blade-cli/lastSuccessfulBuild/artifact/build/generated/p2/index.xml.gz",
				"${FixedIndexedRepo.PROP_CACHE}" : repoPath
			])
			repo.setReporter(new Processor())

			File[] files = repo.get( "com.liferay.blade.cli", "[1,2)" );
			File cliJar = files[0];

			bladeJarPath = cliJar.canonicalPath
		}
		return bladeJarPath
	}

	def setupSpec () {
		println "Starting Server"
		def bladeclijar = getLatestBladeCLIJar()

		executeBlade('server','start');

		OkHttpClient client = new OkHttpClient()
		Request request = new Builder().url("http://localhost:8080").build()

		boolean pingSucceeded = false

		while (!pingSucceeded) {
			try {
				client.newCall(request).execute()
				pingSucceeded = true
			}
			catch( Exception e) {
			}
		}
		println "Server Started"
	}

	def "verify all blade samples"() {
		given:
			def bladeSampleOutputFiles = []
			def bundleIDAllMap = [:]
			def bundleIDStartMap = [:]
			def errorList = []

			System.getProperty('moduleOutputPaths').split(",").each {
				bladeSampleOutputFiles.add(it)
			}

		when:
			bladeSampleOutputFiles.each { sampleBundleFile ->
				def installBundleOutput = executeBlade('sh', 'install', "file:${sampleBundleFile}").text

				def bundleID = installBundleOutput.substring(installBundleOutput.length() - 3)
				def printFileName = new File(sampleBundleFile).name
				printFileName = printFileName.split("-")[0];

				println "Installing ${printFileName}"

				if(installBundleOutput.contains("Failed")) {
					throw new GradleException(installBundleOutput)
				}

				bundleIDAllMap.put(bundleID, printFileName)

				if (new Jar(sampleBundleFile, sampleBundleFile).manifest.mainAttributes.getValue("Fragment-Host") == null) {
					bundleIDStartMap.put(bundleID, printFileName)
				}
			}

			bundleIDStartMap.keySet().each { startBundleID ->
				def startOutput = executeBlade('sh', 'start', startBundleID).text
				def startBundle = bundleIDStartMap[startBundleID]

				println "Starting ${startBundle}"

				if (startOutput.contains('Exception')) {
					errorList.add(startOutput)
				}
			}

		then:
			if (errorList.isEmpty()) {
				println errorList.each
			}
			noExceptionThrown()
			def listBundles = executeBlade('sh', 'lb').text
			println listBundles

		cleanup:
			bundleIDAllMap.keySet().each { allBundleID ->
				def uninstallOutput = executeBlade('sh', 'uninstall', allBundleID).text
				def uninstallBundle = bundleIDAllMap[allBundleID]

				println "Uninstalling ${uninstallBundle}"
			}
	}

	def cleanupSpec(){
		executeBlade('server', 'stop')
		println "Stopping Server"
	}

}