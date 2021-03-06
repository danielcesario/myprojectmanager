package br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Builder
@Generated
public class UserResponse {
    private Long id;
    private String email;
    private String name;

}