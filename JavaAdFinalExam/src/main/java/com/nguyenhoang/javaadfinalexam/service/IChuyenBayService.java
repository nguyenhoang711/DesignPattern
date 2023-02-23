package com.nguyenhoang.javaadfinalexam.service;


import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.CreatingFlightForm;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.FilteringFlightForm;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.UpdatingFlightForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChuyenBayService {
    Page<ChuyenBay> getAllChuyenBay(Pageable pageable, String search, FilteringFlightForm form);

    void deleteChuyenBays(List<Integer> ids);

    void deleteFlight(int id);

    void createChuyenBay(CreatingFlightForm form);

    void updateChuyenBay(UpdatingFlightForm form);
}
