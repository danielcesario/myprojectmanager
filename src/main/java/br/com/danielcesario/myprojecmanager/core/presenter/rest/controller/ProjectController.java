package br.com.danielcesario.myprojecmanager.core.presenter.rest.controller;

import br.com.danielcesario.myprojecmanager.core.business.usecase.project.CreateProject;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjecRequest;
import br.com.danielcesario.myprojecmanager.core.presenter.rest.viewmodel.project.ProjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.danielcesario.myprojecmanager.core.adapter.ProjectAdapter.fromDomaintoResponse;

@Slf4j
@RestController
@RequestMapping(value = "/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final CreateProject createProject;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody final ProjecRequest projecRequest) {
        log.info("Start to create project {}", projecRequest.getName());
        final ProjectResponse projectResponse = fromDomaintoResponse(createProject.execute(projecRequest));
        return new ResponseEntity<>(projectResponse, HttpStatus.CREATED);
    }

}
