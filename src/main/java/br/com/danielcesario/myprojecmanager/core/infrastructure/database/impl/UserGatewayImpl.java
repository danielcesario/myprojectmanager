package br.com.danielcesario.myprojecmanager.core.infrastructure.database.impl;

import br.com.danielcesario.myprojecmanager.core.business.domain.User;
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.repository.UserRepository;
import br.com.danielcesario.myprojecmanager.core.infrastructure.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.danielcesario.myprojecmanager.core.adapter.UserAdapter.fromDomainToModel;
import static br.com.danielcesario.myprojecmanager.core.adapter.UserAdapter.fromModelToDomain;

@Service
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return fromModelToDomain(userRepository.save(fromDomainToModel(user)));
    }

}
