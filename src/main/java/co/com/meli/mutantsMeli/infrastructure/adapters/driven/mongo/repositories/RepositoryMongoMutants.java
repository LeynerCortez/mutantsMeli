package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.repositories;

import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities.Mutants;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryMongoMutants extends MongoRepository<Mutants, String> {
    Long countByisMutant(boolean isMutant);
}
