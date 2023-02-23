package com.nguyenhoang.javaadfinalexam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PhiCong")
@PrimaryKeyJoinColumn(name = "id")

public class PhiCong extends Staff{

    @Column(name = "gioBay", nullable = false)
    private int soGioBay;

    @OneToMany(mappedBy = "phiCong")
    private List<ChuyenBay> flights;

//    @PreRemove
//    private void removeAssociationsWithChilds() {
//        for (ChuyenBay e : flights) {
//            e.setPhiCong(null);
//        }
//    }
}
