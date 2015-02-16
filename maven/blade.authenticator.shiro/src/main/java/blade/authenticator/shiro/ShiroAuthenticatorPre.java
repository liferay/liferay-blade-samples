package blade.authenticator.shiro;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.Authenticator;


@Component(
	property = {
		"key=auth.pipeline.pre"
	},
	service=Authenticator.class
)
public class ShiroAuthenticatorPre implements Authenticator {

	@Activate
	public void activate() {
		Factory<SecurityManager> factory  = new IniSecurityManagerFactory(
						"classpath:userauth.ini");
		SecurityUtils.setSecurityManager(factory.getInstance());
		_log.info("activate");
	}

	@Override
	public int authenticateByEmailAddress(
		long companyId, String emailAddress, String password,
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
						throws AuthException {

		_log.info("authenticateByEmailAddress");

		UsernamePasswordToken usernamePasswordToken =
				new UsernamePasswordToken(emailAddress, password);

		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(usernamePasswordToken);

			boolean authenticated = currentUser.isAuthenticated();

			if (authenticated) {
				_log.info("authenticated");
				return SKIP_LIFERAY_CHECK;
			}
			else {
				return FAILURE;
			}
		}
		catch (AuthenticationException e) {
			_log.error(e.getMessage(), e);
			throw new AuthException(e.getMessage(),e);
		}
	}

	@Override
	public int authenticateByScreenName(
		long companyId, String screenName, String password,
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
						throws AuthException{
		_log.info("authenticateByScreenName  - not implemented ");

		return SUCCESS;
	}

	@Override
	public int authenticateByUserId(
		long companyId, long userId, String password,
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
						throws AuthException{

		_log.info("authenticateByScreenName  - not implemented ");

		return SUCCESS;
	}

	private Log _log = LogFactoryUtil.getLog(ShiroAuthenticatorPre.class);

}
