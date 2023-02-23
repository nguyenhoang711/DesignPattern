package com.nguyenhoang.javaadfinalexam.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ChuyenBay", catalog = "FinalExam")
public class ChuyenBay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "typePlane", nullable = false,updatable = false)
    @Enumerated(EnumType.STRING)
    private PlaneType type;

    @Column(name = "startTime", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "tuyenBay_id")
    private TuyenBay tuyenBay;

    @ManyToOne
    @JoinColumn(name = "phiCong_id")
    private PhiCong phiCong;
}
