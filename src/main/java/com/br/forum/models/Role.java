package com.br.forum.models;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "roles")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID= 1L;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
    @Column(nullable= false, unique= true)
    private String name;

    public Role(String aRoleName) {
        this.name= aRoleName;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
