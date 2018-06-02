package sub.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sub.db.entity.Role;
import sub.web.dto.RoleDto;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("select role from Role role order by role.name")
    List<RoleDto> all();
}
