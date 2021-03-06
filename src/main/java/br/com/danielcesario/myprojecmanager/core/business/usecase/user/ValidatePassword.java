package br.com.danielcesario.myprojecmanager.core.business.usecase.user;

import org.springframework.stereotype.Service;

@Service
public class ValidatePassword {

    public void execute(final String password, final String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("The values not match");
        }
    }

}
