package com.liferay.blade.samples.httpservlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/",
		"osgi.http.whiteboard.servlet.pattern=/blade/httpservlet/*",
	},
	service = Servlet.class
)
public class BladeHttpServlet extends HttpServlet {

	/**
	 * Dummy contents
	 * 
	 * @return dummy contents string
	 */
	protected String generateSampleHTML() {
		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head><title>Sample HTML</title></head>");
		sb.append("<body>");
		sb.append("<h2>Hello World</h2>");
		sb.append("</body>");
		sb.append("</html>");

		return (new String(sb));
	}

	/**
	 * Write sample HTML
	 * 
	 * @param resp
	 */
	protected void writeSampleHTML(HttpServletResponse resp) {
		resp.setCharacterEncoding(StringPool.UTF8);
		resp.setContentType(ContentTypes.TEXT_HTML_UTF8);
		resp.setStatus(HttpServletResponse.SC_OK);

		try {
			ServletResponseUtil.write(resp, generateSampleHTML());
		} catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		}		
	}
	
	@Override
	public void init() throws ServletException {
		if (_log.isInfoEnabled()) {
			_log.info("init1");
		}

		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		if (_log.isInfoEnabled()) {
			_log.info("init2");
		}
		super.init(config);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doPost");
		}
		writeSampleHTML(response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doGet");
		}
		writeSampleHTML(response);
	}

	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doHead");
		}
		super.doHead(request, response);
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doOptions");
		}
		super.doOptions(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doPut");
		}
		super.doPut(request, response);
	}

	@Override
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doTrace");
		}
		super.doTrace(request, response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("service1");
		}
		super.service(request, response);
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("service2");
		}
		super.service(request, response);
	}

	private static final Log _log = LogFactoryUtil.getLog(BladeHttpServlet.class);

	private static final long serialVersionUID = 1L;
}
