package sub.web.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface UserDto {
    Integer getUid();
    String getLogin();
    String getFullName();
    @Value("#{target.role}")
    List<RoleDto> getRole();

    interface RoleDto{
        Integer getId();
        String getName();
    }
}
