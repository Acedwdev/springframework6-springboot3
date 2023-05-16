
package co.com.acedwdev.dao;

import co.com.acedwdev.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<Login, Long>{
    Login findByUsername(String username);
}
