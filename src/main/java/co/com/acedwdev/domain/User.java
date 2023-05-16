package co.com.acedwdev.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty
    private String name;
    
    @NotEmpty
    private String last_name;
    
    @NotEmpty
    @Email
    private String email;
    
    private String phone;
    
    @NotNull
    private Double amount;
}
