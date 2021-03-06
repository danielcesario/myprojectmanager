package br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Builder
@Generated
public class ProjecRequest {
    private Long id;

    @NotBlank
    private Long ownerId;

    @NotBlank
    private String name;

    @NotBlank
    @Min(3)
    private String code;

    @NotBlank
    @Max(255)
    private String description;

}
