/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.samples.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
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
		"osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"
	},
	service = Servlet.class
)
public class BladeServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		if (_log.isInfoEnabled()) {
			_log.info("BladeServlet init");
		}

		super.init();
	}

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		if (_log.isInfoEnabled()) {
			_log.info("doGet");
		}

		_writeSampleHTML(response);
	}

	/**
	 * Dummy contents
	 *
	 * @return dummy contents string
	 */
	private String _generateSampleHTML() {
		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head><title>Sample HTML</title></head>");
		sb.append("<body>");
		sb.append("<h2>Hello World</h2>");
		sb.append("</body>");
		sb.append("</html>");

		return new String(sb);
	}

	/**
	 * Write sample HTML
	 *
	 * @param resp
	 */
	private void _writeSampleHTML(HttpServletResponse resp) {
		resp.setCharacterEncoding(StringPool.UTF8);
		resp.setContentType(ContentTypes.TEXT_HTML_UTF8);
		resp.setStatus(HttpServletResponse.SC_OK);

		try {
			ServletResponseUtil.write(resp, _generateSampleHTML());
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(BladeServlet.class);

	private static final long serialVersionUID = 1L;

}