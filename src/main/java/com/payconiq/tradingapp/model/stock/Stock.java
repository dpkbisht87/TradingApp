package com.payconiq.tradingapp.model.stock;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Stock {
    
    @Id
    private Long id;
    
    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Price is mandatory")
    @NumberFormat(style=Style.CURRENCY)
    private BigDecimal price;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
