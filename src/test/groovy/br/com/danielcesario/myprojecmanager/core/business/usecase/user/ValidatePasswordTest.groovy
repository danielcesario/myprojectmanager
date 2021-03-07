package br.com.danielcesario.myprojecmanager.core.business.usecase.user

import br.com.danielcesario.myprojecmanager.core.utilities.exception.BusinessException
import spock.lang.Specification
import spock.lang.Unroll

class ValidatePasswordTest extends Specification {

    private ValidatePassword validatePassword = new ValidatePassword()

    @Unroll
    def "Validate password when #scenario"() {
        given: "An exception to catch errors"
        BusinessException exception

        when: "I validate the password"
        try {
            validatePassword.execute(password, confirmPassword)
        } catch (BusinessException ex) {
            exception = ex
        }

        then: "Must not thrown any exception"
        if (hasError) {
            exception.message == expectedMessage
        } else {
            exception == null
        }

        where:
        scenario                       | password | confirmPassword | hasError | expectedMessage
        "both are equal"               | "123"    | "123"           | false    | null
        "confirmPassword is different" | "123"    | "456"           | true     | "The values not match"
    }
}
