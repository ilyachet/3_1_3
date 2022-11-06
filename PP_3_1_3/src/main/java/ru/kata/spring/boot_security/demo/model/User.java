package ru.kata.spring.boot_security.demo.model;



import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;


    @Column(name = "username")
    private String username;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.JOIN)
    private Collection<Role> roles;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String name, String surname, String password, int age) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
    }

    public User(String username, String name, String surname, String password, int age, Collection<Role> roles) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!name.equals(user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!password.equals(user.password)) return false;
        if (!username.equals(user.username)) return false;
        return roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + password.hashCode();
        result = 31 * result + age;
        result = 31 * result + username.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }
}
