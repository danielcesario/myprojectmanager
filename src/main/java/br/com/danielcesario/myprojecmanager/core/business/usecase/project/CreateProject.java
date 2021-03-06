package br.com.danielcesario.myprojecmanager.core.business.usecase.project;

import br.com.danielcesario.myprojecmanager.core.business.domain.Project;
import br.com.danielcesario.myprojecmanager.core.infrastructure.gateway.ProjectGateway;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.danielcesario.myprojecmanager.core.adapter.ProjectAdapter.fromRequestToDomain;

@Service
@RequiredArgsConstructor
public class CreateProject {

    private final ProjectGateway projectGateway;

    public Project execute(final ProjecRequest projecRequest) {
        return projectGateway.save(fromRequestToDomain(projecRequest));
    }

}
