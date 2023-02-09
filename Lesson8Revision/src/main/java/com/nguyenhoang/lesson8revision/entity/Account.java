package com.nguyenhoang.lesson8revision.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor

@Entity
@Table(name = "`Account`", catalog = "TestingSystem")
public class Account implements Serializable {

    public static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",length = 50, nullable = false, unique = true)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
