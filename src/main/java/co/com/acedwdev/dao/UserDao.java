
package co.com.acedwdev.dao;

import co.com.acedwdev.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserDao extends CrudRepository<User, Long>{
    
}
