package br.com.danielcesario.myprojecmanager.core.adapter;

import br.com.danielcesario.myprojecmanager.core.business.domain.User;
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.UserModel;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserRequest;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserResponse;

public class UserAdapter {

    public UserAdapter() {
        throw new IllegalArgumentException("Utility class");
    }

    public static UserModel fromDomainToModel(final User source) {
        return UserModel.builder()
                .id(source.getId())
                .email(source.getEmail())
                .password(source.getPassword())
                .name(source.getName())
                .build();
    }

    public static User fromModelToDomain(final UserModel source) {
        return User.builder()
                .id(source.getId())
                .email(source.getEmail())
                .password(source.getPassword())
                .name(source.getName())
                .build();
    }

    public static User fromRequestToDomain(final UserRequest source) {
        return User.builder()
                .id(source.getId())
                .email(source.getEmail())
                .password(source.getPassword())
                .name(source.getName())
                .build();
    }

    public static UserResponse fromDomainToResponse(final User source) {
        return UserResponse.builder()
                .id(source.getId())
                .email(source.getEmail())
                .name(source.getName())
                .build();
    }

}
