package br.com.danielcesario.myprojecmanager.core.business.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Generated
public class Project {
    private Long id;
    private User owner;
    private String name;
    private String code;
    private String description;

}
