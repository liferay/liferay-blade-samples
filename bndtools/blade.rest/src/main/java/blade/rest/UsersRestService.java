
package blade.rest;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

@Component(
	immediate = true,
	property = {
		"jaxrs.application=true"
	},
	service = Application.class)
@Path("/blade.users")
public class UsersRestService extends Application {

	@Override
	public Set<Object> getSingletons() {

		return Collections.<Object> singleton(this);
	}

	@GET
	@Path("/list")
	@Produces("text/plain")
	public String getUsers() {

		StringBuilder result = new StringBuilder();
		for (User user : _userLocalService.getUsers(-1, -1)) {
			result.append(user.getFullName()).append(",\n");
		}
		return result.toString();
	}

	@Reference
	public void setUserLocalService(UserLocalService userLocalService) {

		this._userLocalService = userLocalService;
	}

	private UserLocalService _userLocalService;

}
