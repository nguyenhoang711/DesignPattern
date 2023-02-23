package com.nguyenhoang.javaadfinalexam.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TuyenBay", catalog = "FinalExam")
public class TuyenBay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fromCity", length = 100,nullable = false,updatable = false)
    private String fromCity;

    @Column(name = "toCity", length = 100,nullable = false, updatable = false)
    private String toCity;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "tuyenBay")
    private List<ChuyenBay> flights;
}
