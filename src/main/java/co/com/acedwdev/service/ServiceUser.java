
package co.com.acedwdev.service;

import co.com.acedwdev.domain.User;
import java.util.List;

public interface ServiceUser {
    
    public List<User> userList();
    
    public void save(User user);
    
    public void delete(User user);
    
    public User findUser(User user);
}
