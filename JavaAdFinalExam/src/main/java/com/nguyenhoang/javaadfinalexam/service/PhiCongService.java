package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.CreatingPilotForm;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.FilteringPilotsForm;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.UpdatingPilotsForm;
import com.nguyenhoang.javaadfinalexam.repository.IPhiCongRepository;
import com.nguyenhoang.javaadfinalexam.repository.IStaffRepository;
import com.nguyenhoang.javaadfinalexam.specification.PhiCong.PilotSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PhiCongService implements IPhiCongService{

    @Autowired
    private IPhiCongRepository repository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PhiCong> getAllPilot(Pageable pageable, String search, FilteringPilotsForm form) {
        Specification<PhiCong> where = PilotSpecification.buildWhere(search, form);
        return repository.findAll(where,pageable);
    }

    @Override
    public void createPilot(CreatingPilotForm form) {
        //convert from form to entity
        PhiCong pilot = modelMapper.map(form, PhiCong.class);

        repository.save(pilot);
    }

    @Override
    public void updatePilotInfo(UpdatingPilotsForm form) {
        //convert from form to entity
        PhiCong pilot = modelMapper.map(form, PhiCong.class);

        repository.save(pilot);
    }


}
