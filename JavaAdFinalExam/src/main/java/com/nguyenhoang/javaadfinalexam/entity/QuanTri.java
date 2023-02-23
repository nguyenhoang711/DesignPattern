package com.nguyenhoang.javaadfinalexam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "QuanTri")
@PrimaryKeyJoinColumn(name = "id")
public class QuanTri extends Staff{

    @Column(name = "namKN",nullable = false)
    private int namKN;
}
