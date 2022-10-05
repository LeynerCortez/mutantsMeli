package co.com.meli.mutantsMeli.domain.usecases;

import co.com.meli.mutantsMeli.domain.utils.MutantException;
import co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out.ResponseStats;

public interface UseCaseMutant {
    boolean mutantsValidation(String[] dna) throws MutantException;
    ResponseStats getStats();
}
