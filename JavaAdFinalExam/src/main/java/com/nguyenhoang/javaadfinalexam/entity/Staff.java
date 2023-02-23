package com.nguyenhoang.javaadfinalexam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Staff", catalog = "FinalExam")
public class Staff {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userName", length = 50, unique = true,updatable = false)
    private String username;

    @Column(name = "`password`", length = 100,updatable = false)
    private String password;

    @Column(name = "first_name", length = 50, updatable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, updatable = false)
    private String lastName;

    @Formula(" concat(first_name, ' ', last_name)")
    private String fullName;

    @Column(name = "`role`")
    private String role;

    @Column(name = "gioiTinh",updatable = false)
    private String gender;

    @PrePersist
    public void prePersist(){
        if(password == null) {
            password = new BCryptPasswordEncoder().encode("123456");
        }else {
            password = new BCryptPasswordEncoder().encode(password);
        }
    }
}
