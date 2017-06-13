# Gogo Shell Command

The Gogo Shell Command sample shows how to add a custom command to a Liferay DXP
gogo shell environment.

Any Liferay DXP installation has its own gogo shell environment, allowing system
administrators to interact with Liferay Portal’s module framework on a local
server machine.

This example adds a new custom gogo shell command called `usercount`, under the
`blade` scope, which will print out the number of users currently created on a
specific Liferay DXP installation.

To see this example in action, those are the main steps that need to be
accomplished:

- Have Liferay DXP running
- Connect to your local gogo shell (type `telnet localhost 11311`)
- Type `help` to see all your available commands
- Type `usercount` to execute our new custom command and get how many users we have created on our Liferay DXP installation

![Figure 1: Gogo shell connection and listing all the available commands.](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/gogo-shell-1.png)

![Figure 2: The outcome of executing the usercount command.](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/gogo-shell-2.png)

For this example, to add this new gogo shell command, we've implemented the
logic in a Java simple class , with the following two properties:

- `osgi.command.function=usercount` to specify the command's name and it must match the method name in the registered service implementation
- `osgi.command.scope` for the general scope or namespace for the command

These properties are set in your class's `@Component` annotation like this:

	@Component(
		property = {"osgi.command.function=usercount", "osgi.command.scope=blade"},
		service = Object.class
	)

And the logic is inside the method with the same name as specified in our
`osgi.command.function` property.

	public void usercount() {
		System.out.println(
			"# of users: " + getUserLocalService().getUsersCount());
	}

Lastly, we are using *Declarative Services* to get a reference for the
*UserLocalService* to invoke the *getUsersCount*, which allow us to know how
many users we currently have in the system. 

For more information on using the gogo shell, see the
[Using the Felix Gogo Shell](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
tutorial on Liferay Developer Network.