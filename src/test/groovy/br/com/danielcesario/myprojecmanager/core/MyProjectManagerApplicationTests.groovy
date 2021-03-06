package br.com.danielcesario.myprojecmanager.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = MyProjectManagerApplication)
class MyProjectManagerApplicationTests extends Specification {

    @Autowired
    ApplicationContext context

    def "it should boot Spring application successfully"() {
        expect: "application context exists"
        context != null
    }
}