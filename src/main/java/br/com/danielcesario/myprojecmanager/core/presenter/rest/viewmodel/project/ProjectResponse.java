package br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project;

import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserResponse;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Builder
@Generated
public class ProjectResponse {

    private Long id;
    private String name;
    private String code;
    private String description;
    private UserResponse owner;

}
