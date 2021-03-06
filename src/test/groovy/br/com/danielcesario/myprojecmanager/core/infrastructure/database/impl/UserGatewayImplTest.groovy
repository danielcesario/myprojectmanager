package br.com.danielcesario.myprojecmanager.core.infrastructure.database.impl

import br.com.danielcesario.myprojecmanager.core.business.domain.User
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.UserModel
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.repository.UserRepository
import spock.lang.Specification
import spock.lang.Unroll

class UserGatewayImplTest extends Specification {

    UserRepository userRepository = Mock()
    UserGatewayImpl userGateway = new UserGatewayImpl(userRepository)

    @Unroll
    def "Should try to save an user #scenario"() {
        given: "A valid user to Save"
        def user = new User(null, "user@user.com", "123", "User")

        and: "An exception to receive thrown errors"
        Exception exception = null

        and: "The repository must be called once"
        1 * userRepository.save(_ as UserModel) >> { args ->
            UserModel userParam = (UserModel) args[0]
            assert userParam.id == null
            assert userParam.email == 'user@user.com'
            assert userParam.password == '123'
            assert userParam.name == 'User'

            if (hasError) {
                throw new Exception()
            }
            return new UserModel(1L, "user@user.com", "123", "User")
        }

        when: "I try to save"
        def savedUser
        try {
            savedUser = userGateway.save(user)
        } catch (Exception error) {
            exception = error
        }

        then: "The saved user must be returned"
        if ( hasError ) {
            assert exception != null
        } else {
            assert savedUser.id == 1L
            assert savedUser.email == 'user@user.com'
            assert savedUser.password == '123'
            assert savedUser.name == 'User'
        }

        where:
        scenario         | hasError
        "with success"   | false
        "with exception" | true
    }
}
