package com.nguyenhoang.javaadfinalexam.specification.ChuyenBay;

import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.FilteringFlightForm;
import com.nguyenhoang.javaadfinalexam.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class FlightSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<ChuyenBay> buildWhere(String search, FilteringFlightForm form){
        Specification<ChuyenBay> where = null;
        if (!StringUtils.isEmpty(search)) {

            search = Utils.formatSearch(search);

            CustomFlightSpecification pilotName = new CustomFlightSpecification("coTruong", search);
            where = Specification.where(pilotName);
        }

        //filter theo thoi gian xuat phat nho nhat
        if(form != null && form.getMinStartTime() != null){
            CustomFlightSpecification minStartTime = new CustomFlightSpecification("minStartTime", form.getMinStartTime());
            //check xem cau lenh where da ton tai chua
            if(where == null) where = Specification.where(minStartTime);
            else where = where.and(minStartTime);
        }

        //filter thoi gian xuat phat lon nhat
        if(form != null && form.getMaxStartTime() != null){
            CustomFlightSpecification maxStartTime = new CustomFlightSpecification("maxStartTime", form.getMaxStartTime());

            if(where == null) where = Specification.where(maxStartTime);
            else where = where.and(maxStartTime);
        }
        return where;
    }


}
