package dg.nisum.api.user.infrastructure.persistence.h2;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
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

   @ElementCollection
           @CollectionTable(
                   name = "phones",
                   joinColumns = @JoinColumn(name = "user_id")
           )
    private List<PhoneEntity> phones;

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
