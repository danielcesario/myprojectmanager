package br.com.danielcesario.myprojecmanager.core.business.usecase.user;

import br.com.danielcesario.myprojecmanager.core.adapter.UserAdapter;
import br.com.danielcesario.myprojecmanager.core.business.domain.User;
import br.com.danielcesario.myprojecmanager.core.infrastructure.gateway.UserGateway;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUser {

    private final UserGateway userGateway;
    private final ValidatePassword validatePassword;

    public User execute(final UserRequest userRequest) {
        validatePassword.execute(userRequest.getPassword(), userRequest.getConfirmPassword());
        return userGateway.save(UserAdapter.fromRequestToDomain(userRequest));
    }

}
