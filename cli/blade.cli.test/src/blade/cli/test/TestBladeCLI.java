package blade.cli.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
		
		assertTrue(IO.getFile("generated/test/foo/pom.xml").exists());

		assertTrue(IO.getFile("generated/test/foo/src/main/java/foo/FooPortlet.java").exists());
	}
}
