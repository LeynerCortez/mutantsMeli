package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services;

import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities.Mutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper.StatsMongo;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.repositories.RepositoryMongoMutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services.impl.ServiceMutantsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ServiceMongoMutantsTest {

    static {
        System.setProperty("spring.mongodb.embedded.version","5.0.0");
    }

    @TestConfiguration
    static class ServiceMutantsImplTestContextConfiguration {

        @Bean
        public ServiceMutants serviceMutants() {
            return new ServiceMutantsImpl();
        }
    }

    @Autowired
    private RepositoryMongoMutants repository;

    @Autowired
    private ServiceMutants service;

    @BeforeEach
    void prepareTest() {

        repository.deleteAll();
        for (int i = 0; i <= 9; i++) {
            boolean isMutant = i % 2 == 0;
            System.out.println("isMutant = " + isMutant);
            repository.save(Mutants.builder()
                    .dna(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"})
                    .isMutant(isMutant)
                    .build());
        }
    }

    @Test
    void saveMutantViaService() {
        Mutants mutantSave = Mutants.builder()
                .dna(new String[]{"ATGCGA", "CTGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"})
                .isMutant(true)
                .build();

        service.save(mutantSave);
    }

    @Test
    void getStatsViaService() {

        StatsMongo stats = service.getStats();
        assertEquals(stats.getCountMutantDna(), 5);
    }
}