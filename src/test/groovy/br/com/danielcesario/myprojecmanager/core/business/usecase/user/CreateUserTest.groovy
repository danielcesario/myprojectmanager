package br.com.danielcesario.myprojecmanager.core.business.usecase.user

import br.com.danielcesario.myprojecmanager.core.business.domain.User
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.impl.UserGatewayImpl
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserRequest
import spock.lang.Specification
import spock.lang.Unroll

class CreateUserTest extends Specification {

    private UserGatewayImpl userGateway = Mock()
    private ValidatePassword validatePassword = Mock()
    private CreateUser createUser = new CreateUser(userGateway, validatePassword)

    @Unroll
    def "Should call the CreateUser when #scenario"() {
        given: "A valid UserRequest"
        def request = new UserRequest(null, "user@user.com", "123", "456", "User")

        and: "The ValidatePassword was called"
        callsValidatePassword * validatePassword.execute(_ as String, _ as String) >> { args ->
            assert args[0] == "123"
            assert args[1] == "456"
        }

        and: "The UserGateway was called"
        callsCreateUser * userGateway.save(_ as User) >> { args ->
            assert args[0] instanceof User

            User userParam = (User) args[0]
            assert userParam.id == null
            assert userParam.email == 'user@user.com'
            assert userParam.password == '123'
            assert userParam.name == 'User'

            return new User(1L, "user@user.com", "123", "User")
        }

        when: "I try to create an user"
        def result = createUser.execute(request)

        then:
        result instanceof User
        result.id == 1L
        result.email == 'user@user.com'
        result.password == '123'
        result.name == 'User'

        where:
        scenario              | callsValidatePassword | callsCreateUser | validationError
        "create with success" | 1                     | 1               | false
    }
}
