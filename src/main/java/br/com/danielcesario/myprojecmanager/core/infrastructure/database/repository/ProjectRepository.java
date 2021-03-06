package br.com.danielcesario.myprojecmanager.core.infrastructure.database.repository;

import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.ProjectModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectModel, Long> {
}
