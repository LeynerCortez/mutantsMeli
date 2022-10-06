package co.com.meli.mutantsMeli.domain.usecases;

import co.com.meli.mutantsMeli.domain.usecases.impl.UseCaseMutantImpl;
import co.com.meli.mutantsMeli.domain.utils.IdentifyDna;
import co.com.meli.mutantsMeli.domain.utils.MutantException;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities.Mutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper.StatsMongo;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services.ServiceMutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out.ResponseStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
class UseCaseMutantTest {

    @Mock
    private IdentifyDna identifyDna;

    @InjectMocks
    private UseCaseMutantImpl useCaseMutant;

    @Mock
    private ServiceMutants serviceMutants;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveMutantSuccess() throws MutantException {
        Mutants mutantsTest = Mutants.builder()
                .dna(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"})
                .isMutant(true)
                .build();
        Mockito.doNothing().when(serviceMutants).save(mutantsTest);
        Mockito.when(identifyDna.checkDna(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"})).thenReturn(true
        ).thenReturn(true);
        boolean result = useCaseMutant.mutantsValidation(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"});
        Assertions.assertTrue(result);
    }

    @Test
    void returnStatsSuccessCalculatedRatio() throws MutantException {
        Mockito.when(serviceMutants.getStats())
                .thenReturn(StatsMongo.builder()
                        .countHumanDna(20L)
                        .countMutantDna(10L)
                        .build());
        ResponseStats result = useCaseMutant.getStats();
        Assertions.assertEquals(result.getRatio(), 0.5);
    }
}