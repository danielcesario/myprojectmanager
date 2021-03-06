package br.com.danielcesario.myprojecmanager.core.presenter.rest.controller;

import br.com.danielcesario.myprojecmanager.core.adapter.UserAdapter;
import br.com.danielcesario.myprojecmanager.core.business.usecase.user.CreateUser;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserRequest;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUser createUser;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid final UserRequest userRequest) {
        log.info("Start to create user {}", userRequest.getEmail());
        final UserResponse userResponse = UserAdapter.fromDomainToResponse(createUser.execute(userRequest));
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

}
