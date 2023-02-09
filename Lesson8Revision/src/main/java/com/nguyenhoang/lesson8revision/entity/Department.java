package com.nguyenhoang.lesson8revision.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "`Department`", catalog = "TestingSystem")
public class Department implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`",length = 50, nullable = false, unique = true)
    @NonNull
    private String name;

    @Column(name = "total_member",nullable = false)
    private int totalMember;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "`type`",nullable = false)
    @Convert(converter = DepartmentTypeConverter.class)
    private Type type;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

}
