package br.com.danielcesario.myprojecmanager.core.infrastructure.gateway;

import br.com.danielcesario.myprojecmanager.core.business.domain.User;

public interface UserGateway {

    User save(User user);
}
