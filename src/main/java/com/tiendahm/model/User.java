package com.tiendahm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(nullable = false, length = 60, unique = true)
    private String username; // email

    @Column(nullable = false, length = 60) // 123456789 Bcrypt hash
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    // Relación de muchos a muchos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="id_user", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name="id_role", referencedColumnName = "idRole"))
    private List<Role> roles;
}
