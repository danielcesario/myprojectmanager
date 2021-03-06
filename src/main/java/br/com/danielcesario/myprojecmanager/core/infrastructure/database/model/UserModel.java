package br.com.danielcesario.myprojecmanager.core.infrastructure.database.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldNameConstants(asEnum = true)
@Table(
        name = "tb_user",
        uniqueConstraints = @UniqueConstraint(name = "uk_user__email", columnNames = "email"),
        indexes = @Index(name = "ix_user__email", columnList = "email")
)
@SequenceGenerator(name = "sq_user", sequenceName = "sq_user")
@Generated
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user")
    private Long id;

    private String email;

    private String password;

    private String name;

}
