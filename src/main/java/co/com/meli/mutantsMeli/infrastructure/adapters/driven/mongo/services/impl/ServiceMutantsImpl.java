package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services.impl;

import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities.Mutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper.StatsMongo;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.repositories.RepositoryMongoMutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services.ServiceMutants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMutantsImpl implements ServiceMutants {
    @Autowired
    RepositoryMongoMutants repository;

    public void save(Mutants mutants){
        repository.save(mutants);
    }

    public StatsMongo getStats(){
        return StatsMongo.builder()
                .countHumanDna(repository.count())
                .countMutantDna(repository.countByisMutant(true))
                .build();
    }
}
