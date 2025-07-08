package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean enable=false;
    private String name;
    private String email;
    private String phoneNumber;
    private String passWord;
    @Enumerated
    private Role role;

    @ManyToMany( fetch = FetchType.EAGER)
    private Set<auth.model.MyRole> appRoles=new HashSet<>();

    public void getAppRoles(auth.model.MyRole myRole) {
        this.appRoles.add(myRole);

    }
}
