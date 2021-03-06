package br.com.danielcesario.myprojecmanager.core.infrastructure.database.repository;

import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
}
