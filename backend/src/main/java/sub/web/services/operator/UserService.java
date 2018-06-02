package sub.web.services.operator;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import sub.db.entity.Role;
import sub.db.entity.User;
import sub.web.dto.RoleDto;
import sub.web.dto.UserDto;
import java.util.List;
import java.util.Map;


public interface UserService {
    public List<UserDto>get();
    public User get(Long id);
    public User save(User operator);
    public User update(User operator);
    public Boolean delete(Integer operator);
    User getUserAuth(OAuth2Authentication authentication);
    Map getUserInfo(OAuth2Authentication authentication);
    List<RoleDto> getRole();
}
