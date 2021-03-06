package br.com.danielcesario.myprojecmanager.core.presenter.rest.controller

import br.com.danielcesario.myprojecmanager.core.business.domain.Project
import br.com.danielcesario.myprojecmanager.core.business.usecase.project.CreateProject
import br.com.danielcesario.myprojecmanager.core.fixtures.ProjectFixture
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class ProjectControllerTest extends Specification {

    CreateProject createProject = Mock()
    ProjectController projectController = new ProjectController(createProject)
    MockMvc mockMvc = standaloneSetup(projectController).build()
    ObjectMapper mapper = new ObjectMapper()

    def setup() {
        FixtureFactoryLoader.loadTemplates("br.com.danielcesario.myprojecmanager.core.fixtures")
    }

    def "Should create an Project when call the endpoint"() {
        given: "A valid request"
        ProjecRequest request = Fixture.from(ProjecRequest).gimme(ProjectFixture.NOT_SAVED_PROJECT_REQUEST)

        and:
        1 * createProject.execute(_ as ProjecRequest) >> { args ->
            ProjecRequest param = (ProjecRequest) args[0]
            assert param.id == null
            assert param.name == 'Project One'
            assert param.code == 'P01'
            assert param.description == 'Project One Description'
            assert param.ownerId == 10L

            return Fixture.from(Project).gimme(ProjectFixture.BASIC_PROJECT)
        }

        when: "I call the create endpoint"
        def result = mockMvc.perform(
                post("/projects")
                        .content(mapper.writeValueAsString(request))
                        .contentType("application/json"))
                .andReturn()

        then: "The response status was correct"
        result.response.status == 201

        and: "The json contract was valid"
        result.response.contentAsString == '{"id":1,"name":"Project One","code":"P1","description":"Project One Description","owner":{"id":1,"email":"user@user.com","name":"User"}}'

        and: "The converted response object was valid"
        def content = new JsonSlurper().parseText(result.response.contentAsString)
        content["id"] == 1
        content["name"] == "Project One"
        content["code"] == "P1"
        content["description"] == "Project One Description"
    }
}
