package br.com.danielcesario.myprojecmanager.core.business.usecase.project

import br.com.danielcesario.myprojecmanager.core.business.domain.Project
import br.com.danielcesario.myprojecmanager.core.fixtures.ProjectFixture
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.impl.ProjectGatewayImpl
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import spock.lang.Specification

class CreateProjectTest extends Specification {

    ProjectGatewayImpl projectGateway = Mock()
    CreateProject createProject = new CreateProject(projectGateway)

    def setup() {
        FixtureFactoryLoader.loadTemplates("br.com.danielcesario.myprojecmanager.core.fixtures")
    }

    def "Should create a Project"() {
        given: "A valid ProjectRequest to create"
        ProjecRequest projecRequest = Fixture.from(ProjecRequest).gimme(ProjectFixture.NOT_SAVED_PROJECT_REQUEST)

        and: "The gateway must be called once to save the Project"
        1 * projectGateway.save(_ as Project) >> { args ->
            assert args[0] instanceof Project

            Project project = (Project) args[0]
            assert project.id == null
            assert project.name == 'Project One'
            assert project.code == 'P01'
            assert project.description == 'Project One Description'
            assert project.owner.id == 10L

            return Fixture.from(Project).gimme(ProjectFixture.BASIC_PROJECT)
        }

        when: "I try to execute"
        def result = createProject.execute(projecRequest)

        then: "The result must be valid"
        result instanceof Project
        result.id == 1L
        result.name == 'Project One'
        result.code == 'P1'
        result.description == 'Project One Description'
        result.owner.id == 1L
    }
}
