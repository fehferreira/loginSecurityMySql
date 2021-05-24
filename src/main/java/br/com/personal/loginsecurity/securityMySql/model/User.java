package br.com.personal.loginsecurity.securityMySql.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Length(min = 5, message = "Yout user name must have at least 5 characters")
    @NotEmpty(message = "*Please provide a user name")
    @Column(name = "user_name")
    private String userName;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "Please provide an email")
    @Column(name = "user_email")
    private String email;

    @Length(min = 5, message = "*Yout password must have at least 5 characters")
    @NotEmpty(message = "*Please provide yout password")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "*Please provide yout name")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "*Please provide your last name")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
