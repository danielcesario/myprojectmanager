package br.com.danielcesario.myprojecmanager.core.adapter

import br.com.danielcesario.myprojecmanager.core.business.domain.User
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.UserModel
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserRequest
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserResponse
import spock.lang.Specification

class UserAdapterTest extends Specification {

    def "Shouldn't instantiate the Adapter"() {
        when: "I try to create an instance of UserAdapter"
        UserAdapter adapter = new UserAdapter();

        then: "Must be thrown an IllegalArgumentException"
        thrown(IllegalArgumentException)
    }

    def "Should convert UserModel to User"() {
        given: "A valid UserModel"
        UserModel userModel = new UserModel(1L, "user@user.com", "123", "User")

        when: "I try to convert to User"
        def result = UserAdapter.fromModelToDomain(userModel)

        then: "The result must be valid"
        result instanceof User
        result.id == 1L
        result.email == 'user@user.com'
        result.password == '123'
        result.name == 'User'

        and: "The result entity must have the correct number of fields"
        User.Fields.fields.size() == 4
    }

    def "Should convert User to UserModel"() {
        given: "A valid User"
        User user = new User(1L, "user@user.com", "123", "User")

        when: "I try to convert to UserModel"
        def result = UserAdapter.fromDomainToModel(user)

        then: "The result must be valid"
        result instanceof UserModel
        result.id == 1L
        result.email == 'user@user.com'
        result.password == '123'
        result.name == 'User'

        and: "The result entity must have the correct number of fields"
        UserModel.Fields.fields.size() == 4
    }

    def "Should convert UserRequest to User"() {
        given: "A valid UserRequest"
        def source = new UserRequest(1L, "user@user.com", "123", "123", "User")

        when: "I try to convert to User"
        def result = UserAdapter.fromRequestToDomain(source)

        then: "The result must be valid"
        result instanceof User
        result.id == 1L
        result.email == 'user@user.com'
        result.password == '123'
        result.name == 'User'

        and: "The result entity must have the correct number of fields"
        User.Fields.fields.size() == 4
    }

    def "Should convert User to UserResponse"() {
        given: "A valid User"
        User source = new User(1L, "user@user.com", "123", "User")

        when: "I try to convert to User"
        def result = UserAdapter.fromDomainToResponse(source)

        then: "The result must be valid"
        result instanceof UserResponse
        result.id == 1L
        result.email == 'user@user.com'
        result.name == 'User'

        and: "The result entity must have the correct number of fields"
        UserResponse.Fields.fields.size() == 3
    }
}
