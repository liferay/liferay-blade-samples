package blade.cli.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import aQute.lib.io.IO;
import blade.cli.blade;

@SuppressWarnings("restriction")
public class TestBladeCLI {

	@Before
	public void setup() {
		File testdir = IO.getFile("generated/test");
		if(testdir.exists()) {
			IO.delete(testdir);
			assertFalse(testdir.exists());
		}
	}

	@Test
	public void createMavenPortletProject() throws Exception {
		System.out.println("foo");
		String[] args = new String[] {
			"create",
			"-b",
			"maven",
			"-t",
			"portlet",
			"-d",
			"generated/test",
			"foo"
		};

		new blade().run(args);

		assertTrue(IO.getFile("generated/test/foo").exists());

		assertTrue(IO.getFile("generated/test/foo/bnd.bnd").exists());

		assertTrue(IO.getFile("generated/test/foo/pom.xml").exists());

		File portletFile = IO.getFile("generated/test/foo/src/main/java/foo/FooPortlet.java");

		assertTrue(portletFile.exists());

		String portletFileContent = new String(IO.read(portletFile));

		contains(portletFileContent, "^package foo;.*");

		contains(portletFileContent, ".*javax.portlet.display-name=foo.*");

		contains(portletFileContent, ".*^public class FooPortlet.*");

		contains(portletFileContent, ".*printWriter.print\\(\\\"Foo Portlet.*");

		File pomFile = IO.getFile("generated/test/foo/pom.xml");

		assertTrue(pomFile.exists());

		String pomFileContent = new String(IO.read(pomFile));

		contains(pomFileContent, ".*<groupId>foo</groupId>.*");

		contains(pomFileContent, ".*<artifactId>foo</artifactId>.*");

		contains(pomFileContent, ".*<name>Foo</name>.*");

		File bndFile = IO.getFile("generated/test/foo/bnd.bnd");

		assertTrue(bndFile.exists());

		String bndFileContent = new String(IO.read(bndFile));

		contains(bndFileContent, ".*Private-Package: foo.*");
	}

	private void contains(String content, String pattern) {
		assertTrue(Pattern.compile(pattern, Pattern.MULTILINE | Pattern.DOTALL).matcher(content).matches());
	}
}
