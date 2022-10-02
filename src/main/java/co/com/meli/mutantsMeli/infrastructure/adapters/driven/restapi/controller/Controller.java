package co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.controller;

import co.com.meli.mutantsMeli.domain.usecases.UseCaseMutant;
import co.com.meli.mutantsMeli.domain.utils.MutantException;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.in.RequestMutant;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out.ResponseStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Controller {

    @Autowired
    UseCaseMutant useCaseMutant;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> mutant(@Valid @RequestBody RequestMutant requestMutant) throws MutantException {
        if (useCaseMutant.mutantsValidation(requestMutant.getDna()))
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseStats getStats() {
        return useCaseMutant.getStats();
    }
}
