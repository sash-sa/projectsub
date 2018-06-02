package sub.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sub.db.entity.User;
import sub.web.dto.UserDto;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String username);

    @Query("select user from User user order by user.login")
    List<UserDto> getAll();


}
