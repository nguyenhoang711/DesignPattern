package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import com.nguyenhoang.javaadfinalexam.entity.TuyenBay;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.CreatingFlightLineForm;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.FilteringFlightLineForm;
import com.nguyenhoang.javaadfinalexam.repository.IChuyenBayRepository;
import com.nguyenhoang.javaadfinalexam.repository.ITuyenBayRepository;
import com.nguyenhoang.javaadfinalexam.specification.TuyenBay.TuyenBaySpecification;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.UpdatingFlightLineForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TuyenBayService implements ITuyenBayService{

    @Autowired
    private ITuyenBayRepository repository;

    @Autowired
    private IChuyenBayRepository chuyenBayRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<TuyenBay> getAllTuyenBays(Pageable pageable, String search, FilteringFlightLineForm form) {
        Specification<TuyenBay> where = TuyenBaySpecification.buildWhere(search, form);
        return repository.findAll(where, pageable);
    }

    @Override
    public void createFlightLine(CreatingFlightLineForm form) {
        //convert from form to entity
        TuyenBay entity = modelMapper.map(form, TuyenBay.class);

        repository.save(entity);
    }

    @Override
    public void updateFlightLine(UpdatingFlightLineForm form) {
        //convert from form to entity
        TuyenBay entity = modelMapper.map(form, TuyenBay.class);

        repository.save(entity);
    }

    @Override
    public void deleteTuyenBays(List<Integer> ids) {
        List<TuyenBay> tuyens = repository.findAllById(ids);
        int j;
        List<Integer> flightId;
        for (TuyenBay tuyen: tuyens) {
            List<ChuyenBay> flights = tuyen.getFlights();
            j = flights.size();
            flightId = new ArrayList<>(j);
            while(j > 0){
                flightId.add(flights.get(j - 1).getId());
                j--;
            }
            chuyenBayRepository.deleteAllById(flightId);
        }
        repository.deleteByIds(ids);
    }

    @Override
    public void deleteFlightLine(int id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existByCities(String fromCity, String toCity) {
        return (repository.existsByFromCity(fromCity) && repository.existsByToCity(toCity));
    }

}
