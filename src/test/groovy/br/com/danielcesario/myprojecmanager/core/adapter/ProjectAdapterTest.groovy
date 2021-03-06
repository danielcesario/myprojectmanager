package br.com.danielcesario.myprojecmanager.core.adapter

import br.com.danielcesario.myprojecmanager.core.business.domain.Project
import br.com.danielcesario.myprojecmanager.core.fixtures.ProjectFixture
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.ProjectModel
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjectResponse
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import spock.lang.Specification

class ProjectAdapterTest extends Specification {

    def setup() {
        FixtureFactoryLoader.loadTemplates("br.com.danielcesario.myprojecmanager.core.fixtures")
    }

    def "Shouldn't instantiate the Adapter"() {
        when: "I try to create an instance of ProjectAdapter"
        ProjectAdapter adapter = new ProjectAdapter();

        then: "Must be thrown an IllegalArgumentException"
        thrown(IllegalArgumentException)
    }

    def "Should convert Project to ProjectModel"() {
        given: "A valid Project"
        Project project = Fixture.from(Project).gimme(ProjectFixture.BASIC_PROJECT)

        when: "I try to covert"
        def result = ProjectAdapter.fromDomainToModel(project)

        then: "The result must be a valid ProjectModel"
        result instanceof ProjectModel
        result.id == 1L
        result.name == 'Project One'
        result.code == 'P1'
        result.description == 'Project One Description'
        result.owner.id == 1L

        and: "The source and result entity must have the correct number of fields"
        ProjectModel.Fields.fields.size() == 5
        Project.Fields.fields.size() == 5
    }

    def "Should convert ProjectModel to Project"() {
        given: "A valid ProjectModel"
        ProjectModel project = Fixture.from(ProjectModel).gimme(ProjectFixture.SAVED_PROJECT_MODEL)

        when: "I try to covert"
        def result = ProjectAdapter.fromModelToDomain(project)

        then: "The result must be a valid Project"
        result instanceof Project
        result.id == 1L
        result.name == 'Project One'
        result.code == 'P1'
        result.description == 'Project One Description'
        result.owner.id == 1L

        and: "The source and result entity must have the correct number of fields"
        ProjectModel.Fields.fields.size() == 5
        Project.Fields.fields.size() == 5
    }

    def "Should convert ProjectRequest to Project"() {
        given: "A valid ProjectRequest"
        ProjecRequest project = Fixture.from(ProjecRequest).gimme(ProjectFixture.BASIC_PROJECT_REQUEST)

        when: "I try to covert"
        def result = ProjectAdapter.fromRequestToDomain(project)

        then: "The result must be a valid Project"
        result instanceof Project
        result.id == 1L
        result.name == 'Project One'
        result.code == 'P01'
        result.description == 'Project One Description'
        result.owner.id == 10L

        and: "The source and result entity must have the correct number of fields"
        ProjecRequest.Fields.fields.size() == 5
        Project.Fields.fields.size() == 5
    }

    def "Should convert Project to ProjectResponse"() {
        given: "A valid Project"
        Project project = Fixture.from(Project).gimme(ProjectFixture.BASIC_PROJECT)

        when: "I try to covert"
        def result = ProjectAdapter.fromDomaintoResponse(project)

        then: "The result must be a valid ProjectModel"
        result instanceof ProjectResponse
        result.id == 1L
        result.name == 'Project One'
        result.code == 'P1'
        result.description == 'Project One Description'
        result.owner.id == 1L

        and: "The source and result entity must have the correct number of fields"
        ProjectResponse.Fields.fields.size() == 5
        Project.Fields.fields.size() == 5
    }

}
