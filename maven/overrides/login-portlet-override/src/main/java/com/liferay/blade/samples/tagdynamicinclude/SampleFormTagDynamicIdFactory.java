package com.liferay.blade.samples.tagdynamicinclude;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.servlet.taglib.TagDynamicIdFactory;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true,
	property = {"tagClassName=com.liferay.taglib.aui.FormTag"},
	service = TagDynamicIdFactory.class
)
public class SampleFormTagDynamicIdFactory implements TagDynamicIdFactory{
	public String getTagDynamicId(
		HttpServletRequest request, HttpServletResponse response, Object tag) {

		String portletId = _portal.getPortletId(request);

		if (Validator.isNull(portletId)) {
			return null;
		}

		String name = BeanPropertiesUtil.getStringSilent(tag, "name");

		if (Validator.isNull(name)) {
			return null;
		}

		return portletId.concat(StringPool.DASH).concat(name);
	}

	@Reference
	private Portal _portal;
}
