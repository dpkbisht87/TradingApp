package com.payconiq.tradingapp.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStockRequest {
    @NotNull
    private Integer id;
    
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal price;
}

