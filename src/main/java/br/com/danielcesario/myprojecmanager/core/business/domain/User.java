package br.com.danielcesario.myprojecmanager.core.business.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Builder
@Generated
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;

}
