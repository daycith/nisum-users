package dg.nisum.users.user.infrastructure.persistence.h2;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String id;
    @Column
    private String name;

    @Column
    private String email;

    private String password;

    private String token;

    @Column(name = "created")
    private Date created;
    @Column(name = "modified")
    private Date modified;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "isactive")
    private Boolean isActive;

}
