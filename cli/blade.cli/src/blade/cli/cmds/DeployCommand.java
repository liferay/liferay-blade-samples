package blade.cli.cmds;

import java.io.File;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

import blade.cli.DeployOptions;
import blade.cli.blade;
import blade.cli.util.JMXBundleDeployer;
import aQute.bnd.osgi.Jar;

public class DeployCommand {

	final private blade blade;
	final private DeployOptions options;

	public DeployCommand(blade blade, DeployOptions options) throws Exception {
		this.blade = blade;
		this.options = options;

		List<String> args = options._();

		if (args.size() == 0) {
			// Default command
			printHelp();
		}
		else {
			deploy();
		}
	}

	private void deploy() throws Exception {
		String bundlePath = options._().get(0);

		File bundleFile = new File(bundlePath);

		if (!bundleFile.exists() && !bundleFile.isAbsolute()) {
			bundleFile = new File(blade.getBase(), bundlePath);
		}

		if (!bundleFile.exists()) {
			addError("Deploy", "Unable to find specified bundle file " +
				bundleFile.getAbsolutePath());
			return;
		}


		String bsn = null;

		try (Jar jar = new Jar(bundleFile)) {
			bsn = jar.getBsn();
		}

		if (bsn == null) {
			addError("Deploy", "Unable to determine bsn for file " +
				bundleFile.getAbsolutePath());
			return;
		}

		final JMXBundleDeployer bundleDeployer = getBundleDeployer();

		if (bundleDeployer != null) {
			final long bundleId = bundleDeployer.deploy(bsn, bundleFile);

			if (bundleId > 0) {
				blade.out().println("Installed or updated bundle " + bundleId);
			}
			else {
				addError("Deploy", "Unable to deploy bundle to framework " +
					bundleId);
			}
		}
	}

	private JMXBundleDeployer getBundleDeployer() {
		int port = options.port();

		JMXBundleDeployer bundleDeployer = null;

		try {
			if (port > 0) {
				bundleDeployer = new JMXBundleDeployer(port);
			}
			else {
				bundleDeployer = new JMXBundleDeployer();
			}
		}
		catch (IllegalArgumentException e) {
			addError("Deploy", "Unable to connect to Liferay's OSGi framework");
		}

		return bundleDeployer;
	}

	private void printHelp() throws Exception {
		Formatter f = new Formatter();
		options._command().help(f, this);
		blade.out().println(f);
		f.close();
	}

	private void addError(String prefix, String msg) {
		blade.addErrors(prefix, Collections.singleton(msg));
	}
}
