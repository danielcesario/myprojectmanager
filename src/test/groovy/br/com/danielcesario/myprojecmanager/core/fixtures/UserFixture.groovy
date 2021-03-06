package br.com.danielcesario.myprojecmanager.core.fixtures

import br.com.danielcesario.myprojecmanager.core.business.domain.User
import br.com.danielcesario.myprojecmanager.core.infrastructure.database.model.UserModel
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader

class UserFixture implements TemplateLoader {

    public static final String BASIC_USER = "Basic User"
    public static final String NOT_SAVED_USER = "Not saved User"

    public static final String SAVED_USER_MODEL = "Saved User Model"

    @Override
    void load() {
        Fixture.of(User).addTemplate(BASIC_USER, new Rule() {
            {
                add("id", 1L)
                add("name", "User")
                add("email", "user@user.com")
                add("password", "12345678")
            }
        })

        Fixture.of(User).addTemplate(NOT_SAVED_USER).inherits(BASIC_USER, new Rule() {
            {
                add("id", null)
            }
        })

        Fixture.of(UserModel).addTemplate(SAVED_USER_MODEL, new Rule() {
            {
                add("id", 1L)
                add("name", "User")
                add("email", "user@user.com")
                add("password", "12345678")
            }
        })
    }
}
