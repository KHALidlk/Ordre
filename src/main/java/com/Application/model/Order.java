package com.Application.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Order {
    private int id;
    private LocalDateTime date;
    private BigDecimal amount;
    private int customerId;
}
