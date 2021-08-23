package com.payconiq.tradingapp.model.stock;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Stock {
    
    @Id
    private Integer id;
    
    @NotNull
    private String name;
    
    @NotNull
    private BigDecimal price;
    
    @NotNull
    private Date lastUpdate;
}
