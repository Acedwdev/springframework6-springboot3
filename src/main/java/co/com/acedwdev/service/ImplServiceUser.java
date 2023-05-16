
package co.com.acedwdev.service;

import co.com.acedwdev.dao.UserDao;
import co.com.acedwdev.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImplServiceUser implements ServiceUser{
    
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> userList() {
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(User user) {
        return userDao.findById(user.getIdUser()).orElse(null);
    }    
    
}
