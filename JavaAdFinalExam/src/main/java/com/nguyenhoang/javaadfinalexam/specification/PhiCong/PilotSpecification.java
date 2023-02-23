package com.nguyenhoang.javaadfinalexam.specification.PhiCong;


import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.FilteringPilotsForm;
import com.nguyenhoang.javaadfinalexam.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class PilotSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<PhiCong> buildWhere(String search, FilteringPilotsForm form){
        Specification<PhiCong> where = null;
        //search by fullName
        if(!StringUtils.isEmpty(search)){
            search = Utils.formatSearch(search);

            CustomPilotSpecification fullName = new CustomPilotSpecification("fullName",search);
            where =Specification.where(fullName);
        }

        //filter by Gender
        if(form != null && form.getGender() != null){
            CustomPilotSpecification gender =new CustomPilotSpecification("gender",form.getGender());

            if(where != null) where = where.and(gender);
            else where = Specification.where(gender);
        }

        //filter by min so gio bay
        if(form != null && form.getMinSoGiobay() != null){
            CustomPilotSpecification minGioBay = new CustomPilotSpecification("minSoGioBay",form.getMinSoGiobay());

            if(where != null) where = where.and(minGioBay);
            else where = Specification.where(minGioBay);
        }

        //filter by max so gio bay
        if(form != null && form.getMaxSoGioBay() != null){
            CustomPilotSpecification maxGiobay = new CustomPilotSpecification("maxSoGioBay", form.getMaxSoGioBay());

            if(where != null) where = where.and(maxGiobay);
            else where = Specification.where(maxGiobay);
        }

        return where;
    }
}
