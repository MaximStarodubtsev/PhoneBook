package com.example.spring.DTO;

import lombok.Builder;

@Builder
public record PCDTO(String model,
                    String HDD,
                    String RAM,
                    String invNum) {
}
