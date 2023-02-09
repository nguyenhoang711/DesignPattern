package com.nguyenhoang.lesson8revision.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {
    @Bean
    //dùng để tạo modelMapper để mapping các đối tượng theo tên
    public ModelMapper initModelMapper(){
        return new ModelMapper();
    }
}
