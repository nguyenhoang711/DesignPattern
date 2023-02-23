package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.TuyenBay;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.CreatingFlightLineForm;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.FilteringFlightLineForm;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.UpdatingFlightLineForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITuyenBayService {
    Page<TuyenBay> getAllTuyenBays(Pageable pageable, String where, FilteringFlightLineForm form);

    void createFlightLine(CreatingFlightLineForm form);

    void updateFlightLine(UpdatingFlightLineForm form);

    void deleteTuyenBays(List<Integer> ids);

    void deleteFlightLine(int id);

    boolean existByCities(String fromCity, String toCity);
}
