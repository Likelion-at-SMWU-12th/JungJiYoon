package study.querydsl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {

    @NotBlank(groups = ValidationGroup1.class)
    private String name;

    @Positive(groups = ValidationGroup2.class)
    private int price;

    @Min(value=1, groups = ValidationGroup2.class)
    private int stock;
}
