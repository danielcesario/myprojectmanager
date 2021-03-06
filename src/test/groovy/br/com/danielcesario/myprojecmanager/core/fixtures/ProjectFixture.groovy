package br.com.danielcesario.myprojecmanager.core.fixtures

import br.com.danielcesario.myprojecmanager.core.business.domain.Project
import br.com.danielcesario.myprojecmanager.core.business.domain.User
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.ProjectModel
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.UserModel
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

class ProjectFixture implements TemplateLoader {

    public static final String BASIC_PROJECT = "Basic Project"
    public static final String NOT_SAVED_PROJECT = "Not saved Project"

    public static final String SAVED_PROJECT_MODEL = "Saved Project Model"

    public static final String BASIC_PROJECT_REQUEST = "Basic Project Request"
    public static final String NOT_SAVED_PROJECT_REQUEST = "Not saved Project Request"

    @Override
    void load() {
        Fixture.of(Project).addTemplate(BASIC_PROJECT, new Rule() {
            {
                add("id", 1L)
                add("owner", one(User, UserFixture.BASIC_USER))
                add("name", "Project One")
                add("code", "P1")
                add("description", "Project One Description")
            }
        })

        Fixture.of(Project).addTemplate(NOT_SAVED_PROJECT).inherits(BASIC_PROJECT, new Rule() {
            {
                add("id", null)
            }
        })

        Fixture.of(ProjectModel).addTemplate(SAVED_PROJECT_MODEL, new Rule() {
            {
                add("id", 1L)
                add("owner", one(UserModel, UserFixture.SAVED_USER_MODEL))
                add("name", "Project One")
                add("code", "P1")
                add("description", "Project One Description")
            }
        })

        Fixture.of(ProjecRequest).addTemplate(BASIC_PROJECT_REQUEST, new Rule() {
            {
                add(ProjecRequest.Fields.id, 1L)
                add(ProjecRequest.Fields.ownerId, 10L)
                add(ProjecRequest.Fields.name, "Project One")
                add(ProjecRequest.Fields.code, "P01")
                add(ProjecRequest.Fields.description, "Project One Description")
            }
        })

        Fixture.of(ProjecRequest).addTemplate(NOT_SAVED_PROJECT_REQUEST).inherits(BASIC_PROJECT_REQUEST, new Rule() {
            {
                add(ProjecRequest.Fields.id, null)
            }
        })
    }

}