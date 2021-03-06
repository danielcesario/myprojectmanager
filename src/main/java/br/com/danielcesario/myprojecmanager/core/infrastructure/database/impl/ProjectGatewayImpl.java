package br.com.danielcesario.myprojecmanager.core.infrastructure.database.impl;

import br.com.danielcesario.myprojecmanager.core.business.domain.Project;
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.repository.ProjectRepository;
import br.com.danielcesario.myprojecmanager.core.infrastructure.gateway.ProjectGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.danielcesario.myprojecmanager.core.adapter.ProjectAdapter.fromDomainToModel;
import static br.com.danielcesario.myprojecmanager.core.adapter.ProjectAdapter.fromModelToDomain;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectGatewayImpl implements ProjectGateway {

    private final ProjectRepository projectRepository;

    @Override
    public Project save(final Project project) {
        try {
            return fromModelToDomain(projectRepository.save(fromDomainToModel(project)));
        } catch (final Exception ex) {
            log.error("Error on save project {}", project.getName(), ex);
            throw new RuntimeException("Error on save project");
        }
    }

}
