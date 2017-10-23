package com.liferay.blade.samples.httpservlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
		"dynamic.data.mapping.form.builder.servlet=true",
		"osgi.http.whiteboard.context.path=/",
		"osgi.http.whiteboard.servlet.name=liferay.blade.samples.httpservlet.BladeHttpServlet",
		"osgi.http.whiteboard.servlet.pattern=/blade/httpservlet/*",
	},
	service = Servlet.class
)
public class BladeHttpServlet extends HttpServlet {
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
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doPost");
		}
		super.doPost(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doGet");
		}
		super.doGet(req, resp);
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doHead");
		}
		super.doHead(req, resp);
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doOptions");
		}
		super.doOptions(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doPut");
		}
		super.doPut(req, resp);
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("doTrace");
		}
		super.doTrace(req, resp);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("service1");
		}
		super.service(req, resp);
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		if (_log.isInfoEnabled()) {
			_log.info("service2");
		}
		super.service(req, res);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(
			BladeHttpServlet.class);
	
	private static final long serialVersionUID = 1L;
}
