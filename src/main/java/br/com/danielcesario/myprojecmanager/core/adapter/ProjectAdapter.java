package br.com.danielcesario.myprojecmanager.core.adapter;

import br.com.danielcesario.myprojecmanager.core.business.domain.Project;
import br.com.danielcesario.myprojecmanager.core.business.domain.User;
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.ProjectModel;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjectResponse;

public class ProjectAdapter {

    private ProjectAdapter() {
        throw new IllegalStateException("Utility class");
    }

    public static ProjectModel fromDomainToModel(final Project source) {
        return ProjectModel.builder()
                .id(source.getId())
                .name(source.getName())
                .code(source.getCode())
                .description(source.getDescription())
                .owner(UserAdapter.fromDomainToModel(source.getOwner()))
                .build();
    }

    public static Project fromModelToDomain(final ProjectModel source) {
        return Project.builder()
                .id(source.getId())
                .name(source.getName())
                .code(source.getCode())
                .description(source.getDescription())
                .owner(UserAdapter.fromModelToDomain(source.getOwner()))
                .build();
    }

    public static Project fromRequestToDomain(final ProjecRequest source) {
        return Project.builder()
                .id(source.getId())
                .name(source.getName())
                .code(source.getCode())
                .description(source.getDescription())
                .owner(User.builder().id(source.getOwnerId()).build())
                .build();
    }

    public static ProjectResponse fromDomaintoResponse(final Project source) {
        return ProjectResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .code(source.getCode())
                .description(source.getDescription())
                .owner(UserAdapter.fromDomainToResponse(source.getOwner()))
                .build();
    }

}
