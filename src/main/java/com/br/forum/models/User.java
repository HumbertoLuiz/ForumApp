package com.br.forum.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded= true)
@ToString(onlyExplicitlyIncluded= true)
@Table(name= "users")
public class User {

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false)
    private String name;

    @Column(nullable= false, unique= true)
    private String email;

    @Column(nullable= false)
    private String password;

    private String real_password;

    @ManyToMany(fetch= FetchType.EAGER, cascade= (CascadeType.ALL))
    private Set<Role> roles= new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }
}
