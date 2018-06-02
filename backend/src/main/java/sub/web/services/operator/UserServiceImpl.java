package sub.web.services.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import sub.db.entity.Issue;
import sub.db.entity.User;
import sub.db.repo.RoleRepository;
import sub.db.repo.UserRepository;
import sub.security.AppPasswordEncoder;
import sub.web.dto.RoleDto;
import sub.web.dto.UserDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserDto> get() {
        return userRepository.getAll();
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User save(User operator) {
        operator.setPassword(new AppPasswordEncoder().encode(operator.getPassword()));
        return userRepository.save(operator);
    }

    @Override
    public User update(User operator) {
        return userRepository.save(operator);
    }

    @Override
    public Boolean delete(Integer id) {
        User operator=userRepository.findById(id).get();
        List<Issue> issues=operator.getIssues();
        if(issues.size()==0){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getUserAuth(OAuth2Authentication authentication) {
        return userRepository.findByLogin(((UserDetails) authentication.getUserAuthentication().getPrincipal()).getUsername());
    }

    @Override
    public Map getUserInfo(OAuth2Authentication authentication) {
        User user = getUserAuth(authentication);
        Map<String, Object> response = new HashMap<>();
        response.put("login", user.getLogin());
        response.put("user", user.getUid());
        response.put("userName", user.getFullName());
        return response;
    }

    @Override
    public List<RoleDto> getRole() {
        return roleRepository.all();
    }
}
