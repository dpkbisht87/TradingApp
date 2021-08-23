package com.payconiq.tradingapp.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStockRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int id;
    
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String name;
    
    @NotNull(message = "{constraints.NotEmpty.message}")
    private BigDecimal price;
}

