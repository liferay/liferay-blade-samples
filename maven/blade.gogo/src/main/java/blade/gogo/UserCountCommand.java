package blade.gogo;

import com.liferay.portal.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
        property = {
                "osgi.command.scope=blade",
                "osgi.command.function=usercount"
        },
        service = Object.class
)
public class UserCountCommand {
    private UserLocalService _userLocalService;

    public void usercount() {
        System.out.println("# of users: "+getUserLocalService().getUsersCount());
    }

    public UserLocalService getUserLocalService() {
        return _userLocalService;
    }

    @Reference
    public void setUserLocalService(UserLocalService _userLocalService) {
        this._userLocalService = _userLocalService;
    }
}
