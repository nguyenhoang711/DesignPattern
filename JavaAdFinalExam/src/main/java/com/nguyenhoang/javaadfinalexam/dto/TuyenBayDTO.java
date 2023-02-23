package com.nguyenhoang.javaadfinalexam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TuyenBayDTO {
    private int id;

    private String fromCity;

    private String toCity;

    private double price;

//    private List<ChuyenBayDTO> flights;
//
//    @Data
//    @NoArgsConstructor
//    static class ChuyenBayDTO{
//        @JsonProperty("flightId")
//        private int id;
//    }
}
