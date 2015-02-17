package blade.portlet;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.model.PortletApp;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-greeter",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Greeter Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.ftl",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GreeterPortlet extends FreeMarkerPortlet {

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletConfig;

		com.liferay.portal.model.Portlet portlet =
			liferayPortletConfig.getPortlet();

		PortletApp portletApp = portlet.getPortletApp();

		ServletContextPool.put(
			portletApp.getServletContextName(), portletApp.getServletContext());
	}

	@Override
	public void destroy() {
		PortletContext portletContext = getPortletContext();

		ServletContextPool.remove(portletContext.getPortletContextName());

		super.destroy();
	}
}
