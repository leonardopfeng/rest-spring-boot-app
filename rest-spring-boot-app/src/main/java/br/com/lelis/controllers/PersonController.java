package br.com.lelis.controllers;

import java.util.List;

import br.com.lelis.data.vo.PersonVO;
import br.com.lelis.data.vo.v2.PersonVOV2;
import br.com.lelis.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;


    @GetMapping(produces = {
            br.com.lelis.util.MediaType.APPLICATION_JSON,
            br.com.lelis.util.MediaType.APPLICATION_XML,
            br.com.lelis.util.MediaType.APPLICATION_YML
    }
    )
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            },
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            },
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return service.createV2(person);
    }

    @PutMapping(
            consumes = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            },
            produces = {
                    br.com.lelis.util.MediaType.APPLICATION_JSON,
                    br.com.lelis.util.MediaType.APPLICATION_XML,
                    br.com.lelis.util.MediaType.APPLICATION_YML
            })
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    // adding ResponseEntity<?> we can return the right error code
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        // returns 204 code
        return ResponseEntity.noContent().build();
    }
}