package co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.controller;

import co.com.meli.mutantsMeli.domain.usecases.UseCaseMutant;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.in.RequestMutant;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out.ResponseStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ControllerTest {

    @Mock
    UseCaseMutant useCaseMutant;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenIsMutantWithStatus200() throws Exception {
        Mockito.when(useCaseMutant.mutantsValidation(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"})).thenReturn(true);
        RequestMutant request = new RequestMutant();
        request.setDna(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"});
        ResponseEntity<?> response = controller.mutant(request);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK), response);
    }

    @Test
    void noIsMutantWithStatus403() throws Exception {
        Mockito.when(useCaseMutant.mutantsValidation(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"})).thenReturn(false);
        RequestMutant request = new RequestMutant();
        request.setDna(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"});
        ResponseEntity<?> response = controller.mutant(request);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), response);
    }

    @Test
    void getStatsNotNull() {
        Mockito.when(useCaseMutant.getStats()).thenReturn(ResponseStats.builder()
                        .countHumanDna(175L)
                        .countMutantDna(50L)
                        .ratio(0.75)
                .build());
        ResponseStats response = controller.getStats();
        Assertions.assertNotNull(response);
    }

}