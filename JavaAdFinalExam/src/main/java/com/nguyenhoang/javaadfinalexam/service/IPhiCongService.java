package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.CreatingPilotForm;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.FilteringPilotsForm;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.UpdatingPilotsForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPhiCongService {
    public Page<PhiCong> getAllPilot(Pageable pageable, String search, FilteringPilotsForm form);

    void createPilot(CreatingPilotForm form);

    void updatePilotInfo(UpdatingPilotsForm form);
}
