package io.github.mariazevedo88.travelsapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.mariazevedo88.travelsapi.enumeration.ClTravelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClonedTravel {
    
    private Long id;
    private String orderNumber;
    private BigDecimal amount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ClTravelTypeEnum type;

    public ClonedTravel(ClTravelTypeEnum type) {
        this.type = type;
    }

    
}
