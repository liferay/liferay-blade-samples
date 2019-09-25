<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<aui:input label="minimum-number-of-products" name="typeSettings" type="text">
	<aui:validator name="digits" />
	<aui:validator name="min">1</aui:validator>
</aui:input>