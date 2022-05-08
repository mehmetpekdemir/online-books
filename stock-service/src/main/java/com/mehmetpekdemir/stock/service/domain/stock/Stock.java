package com.mehmetpekdemir.stock.service.domain.stock;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    private String stockUid;
    private String bookUid;
    private Integer amount;
}
