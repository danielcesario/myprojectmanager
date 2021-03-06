package br.com.danielcesario.myprojecmanager.core.infrastructure.gateway;

import br.com.danielcesario.myprojecmanager.core.business.domain.Project;

public interface ProjectGateway {

    Project save(final Project project);

}
