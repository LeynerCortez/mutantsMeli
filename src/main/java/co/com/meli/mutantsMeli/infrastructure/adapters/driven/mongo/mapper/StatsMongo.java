package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StatsMongo {
    private Long countMutantDna;
    private Long countHumanDna;
}
