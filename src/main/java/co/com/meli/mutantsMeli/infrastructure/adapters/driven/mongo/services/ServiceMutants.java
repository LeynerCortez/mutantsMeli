package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services;

import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities.Mutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper.StatsMongo;

public interface ServiceMutants {
    void save(Mutants mutants);
    StatsMongo getStats();
}
