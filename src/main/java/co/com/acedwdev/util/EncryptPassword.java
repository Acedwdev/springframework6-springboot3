
package co.com.acedwdev.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptPassword {
    public static void main(String[] args) {
        
        var password = "123";
        System.out.println("password: " + password);
        System.out.println("encrypt password:" + encryptPassword(password));
    }
    
    public static String encryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
