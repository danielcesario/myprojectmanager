package br.com.danielcesario.myprojecmanager.core.infrastructure.database.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Entity
@Table(
        name = "tb_project",
        uniqueConstraints = @UniqueConstraint(name = "uk_project__code", columnNames = "code"),
        indexes = @Index(name = "ix_user__code", columnList = "code")
)
@SequenceGenerator(name = "sq_project", sequenceName = "sq_project")
@Generated
public class ProjectModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_project")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "fk_project__user")
    )
    private UserModel owner;

    private String name;

    private String code;

    private String description;

}
