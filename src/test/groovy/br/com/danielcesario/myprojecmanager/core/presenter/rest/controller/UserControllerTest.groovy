package br.com.danielcesario.myprojecmanager.core.presenter.rest.controller

import br.com.danielcesario.myprojecmanager.core.business.domain.User
import br.com.danielcesario.myprojecmanager.core.business.usecase.user.CreateUser
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class UserControllerTest extends Specification {

    CreateUser createUser = Mock()
    UserController userController = new UserController(createUser)
    MockMvc mockMvc = standaloneSetup(userController).build()
    ObjectMapper mapper = new ObjectMapper()

    def "Should create an User when call the endpoint"() {
        given: "A valid request"
        def request = new UserRequest(null, "user@user.com", "12345678", "12345678", "User")

        and:
        1 * createUser.execute(_ as UserRequest) >> new User(1L, "user@user.com", "12345678", "User")

        when: "I call the create endpoint"
        def result = mockMvc.perform(
                post("/users")
                        .content(mapper.writeValueAsString(request))
                        .contentType("application/json"))
                .andReturn()

        then: "The response status was correct"
        result.response.status == 201

        and: "The json contract was valid"
        result.response.contentAsString == '{"id":1,"email":"user@user.com","name":"User"}'

        and: "The converted response object was valid"
        def content = new JsonSlurper().parseText(result.response.contentAsString)
        content["id"] == 1
        content["name"] == "User"
        content["email"] == "user@user.com"
    }
}
