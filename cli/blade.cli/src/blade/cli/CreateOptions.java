package blade.cli;

import java.io.File;

import org.osgi.framework.Version;

import aQute.lib.getopt.Arguments;
import aQute.lib.getopt.Description;
import aQute.lib.getopt.Options;
import blade.cli.cmds.Build;
import blade.cli.cmds.IDE;
import blade.cli.cmds.Type;

@Arguments(arg = "name")
@Description("Creates a new Liferay module project.")
public interface CreateOptions extends Options {
	@Description("The directory where to create the new project.")
	File dir();

	@Description("The build type of project to create.  Valid values are maven or gradle")
	Build build();

	@Description("The type of IDE metadata to create along side the new project.")
	IDE ide();

	@Description("The type of extension to Liferay, valid values are: portlet, service.hook")
	Type type();

	@Description("Generates a service component class")
	String service();

	@Description("The version of Liferay to create the module for, by default its 7.0.0")
	Version version();
}