package br.com.danielcesario.myprojecmanager.core.infrastructure.database.impl

import br.com.danielcesario.myprojecmanager.core.business.domain.Project
import br.com.danielcesario.myprojecmanager.core.fixtures.ProjectFixture
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.ProjectModel
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.repository.ProjectRepository
import br.com.danielcesario.myprojecmanager.core.utilities.exception.GatewayException
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import spock.lang.Specification
import spock.lang.Unroll

class ProjectGatewayImplTest extends Specification {

    ProjectRepository projectRepository = Mock()
    ProjectGatewayImpl projectGateway = new ProjectGatewayImpl(projectRepository)

    def setup() {
        FixtureFactoryLoader.loadTemplates("br.com.danielcesario.myprojecmanager.core.fixtures")
    }

    @Unroll
    def "Should try to save a project #scenario"() {
        given: "A valid Project to save"
        Project project = Fixture.from(Project).gimme(ProjectFixture.NOT_SAVED_PROJECT)

        and: "An exception to receive thrown errors"
        Exception exception = null

        and: "The repository must return the saved object"
        1 * projectRepository.save(_ as ProjectModel) >> { args ->
            assert args[0] instanceof ProjectModel

            def param = (ProjectModel) args[0]
            assert param.id == null
            assert param.name == project.name
            assert param.code == project.code
            assert param.description == project.description
            assert param.owner.email == project.owner.email

            if (hasError) {
                throw new GatewayException("message")
            }
            return Fixture.from(ProjectModel).gimme(ProjectFixture.SAVED_PROJECT_MODEL)
        }

        when: "I try to save a Project"
        def result
        try {
            result = projectGateway.save(project)
        } catch (Exception error) {
            exception = error
        }

        then: "The result must be valid"
        if (hasError) {
            assert exception != null
            assert exception instanceof GatewayException
        } else {
            result instanceof Project
            result.id == 1L
            result.name == "Project One"
            result.code == "P1"
            result.description == "Project One Description"
            result.owner.email == "user@user.com"
        }

        where:
        scenario         | hasError
        "with success"   | false
        "with exception" | true
    }
}
