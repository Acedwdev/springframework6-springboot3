
package co.com.acedwdev.service;

import co.com.acedwdev.dao.LoginDao;
import co.com.acedwdev.domain.Login;
import co.com.acedwdev.domain.Role;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class ServiceLogin implements UserDetailsService{
    
    @Autowired
    private LoginDao loginDao;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginDao.findByUsername(username);
        
        if(login == null){
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Role role: login.getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return new User(login.getUsername(), login.getPassword(), roles);
    
    }
    
}
