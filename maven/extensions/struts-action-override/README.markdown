## An example of overriding Struts Actions in Liferay

Though most usage of Struts Actions have been removed from Liferay, many still exist such as the password update feature after login for instance.

This module works by overriding the existing struts action for password update. **BaseStructsAction** was deprecated from the product in Liferay 7.1, which was the old way to override a Struts Action. This example implements the interface **StrutsAction** instead.

All code is in a single class, feel free to resuse, the code comes without warranty or support from myself or Liferay.