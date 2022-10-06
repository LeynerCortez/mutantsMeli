package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.mapper;

import co.com.meli.mutantsMeli.config.Generated;
import lombok.Builder;
import lombok.Data;

@Generated
@Data
@Builder(toBuilder = true)
public class StatsMongo {
    private Long countMutantDna;
    private Long countHumanDna;
}
