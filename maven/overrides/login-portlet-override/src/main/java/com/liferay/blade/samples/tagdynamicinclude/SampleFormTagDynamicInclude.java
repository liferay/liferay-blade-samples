package com.liferay.blade.samples.tagdynamicinclude;

import com.liferay.portal.kernel.servlet.taglib.TagDynamicInclude;
import com.liferay.portal.kernel.util.PortletKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = TagDynamicInclude.class)
public class SampleFormTagDynamicInclude implements TagDynamicInclude{

	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String tagClassName, String tagDynamicId, String tagPoint)
		throws IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.println(
			"<h2>Liferay Portal Taglib AUI Form Extension Sample</h2><br />");
	}

	public void register(TagDynamicIncludeRegistry tagDynamicIncludeRegistry) {
		tagDynamicIncludeRegistry.register(
			"com.liferay.taglib.aui.FormTag", PortletKeys.LOGIN + "-loginForm",
			"doStartTag#before");

		tagDynamicIncludeRegistry.register(
			"com.liferay.taglib.aui.FormTag",
			PortletKeys.LOGIN + "-loginFormModal", "doStartTag#before");
	}
}
