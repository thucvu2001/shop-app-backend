package com.project.shopapp.resposes;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data//toString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private List<ProductResponse> products;
    private int totalPages;
}
