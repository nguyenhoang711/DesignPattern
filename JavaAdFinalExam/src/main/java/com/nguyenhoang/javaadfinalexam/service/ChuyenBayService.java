package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.CreatingFlightForm;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.FilteringFlightForm;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.UpdatingFlightForm;
import com.nguyenhoang.javaadfinalexam.repository.IChuyenBayRepository;
import com.nguyenhoang.javaadfinalexam.specification.ChuyenBay.FlightSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ChuyenBayService implements IChuyenBayService{

    @Autowired
    private IChuyenBayRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ChuyenBay> getAllChuyenBay(Pageable pageable, String search, FilteringFlightForm form) {
        Specification<ChuyenBay> where = FlightSpecification.buildWhere(search,form);
        return repository.findAll(where,pageable);
    }

    @Override
    public void deleteChuyenBays(List<Integer> ids) {
        repository.deleteChuyenBays(ids);
    }

    @Override
    public void deleteFlight(int id) {
        repository.deleteById(id);
    }

    @Override
    public void createChuyenBay(CreatingFlightForm form) {
        //omit id field
        TypeMap<CreatingFlightForm, ChuyenBay> typeMap = modelMapper.getTypeMap(CreatingFlightForm.class,ChuyenBay.class);
        if(typeMap == null){
            modelMapper.addMappings(new PropertyMap<CreatingFlightForm, ChuyenBay>() {

                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        //convert from form to entity
        ChuyenBay flight = modelMapper.map(form, ChuyenBay.class);
        repository.save(flight);
    }

    @Override
    public void updateChuyenBay(UpdatingFlightForm form) {
        ChuyenBay flight = modelMapper.map(form,ChuyenBay.class);
        repository.save(flight);
    }
}
