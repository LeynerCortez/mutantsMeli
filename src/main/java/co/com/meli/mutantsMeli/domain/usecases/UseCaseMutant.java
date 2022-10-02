package co.com.meli.mutantsMeli.domain.usecases;

import co.com.meli.mutantsMeli.domain.utils.IdentifyDna;
import co.com.meli.mutantsMeli.domain.utils.MutantException;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities.Mutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper.StatsMongo;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.services.ServiceMongoMutants;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out.ResponseStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseCaseMutant {

    @Autowired
    IdentifyDna identifyDna;

    @Autowired
    ServiceMongoMutants serviceMongoMutants;

    public boolean mutantsValidation(String[] dna) throws MutantException {

        Mutants mutants = Mutants.builder()
                .isMutant(identifyDna.checkDna(dna))
                .dna(dna)
                .build();

        serviceMongoMutants.save(mutants);
        return mutants.isMutant();
    }

    public ResponseStats getStats(){
        StatsMongo statsMongo = serviceMongoMutants.getStats();
        return ResponseStats.builder()
                .countHumanDna(statsMongo.getCountHumanDna())
                .countMutantDna(statsMongo.getCountMutantDna())
                .ratio((double) statsMongo.getCountMutantDna()/(double) statsMongo.getCountHumanDna())
                .build();
    }
}
