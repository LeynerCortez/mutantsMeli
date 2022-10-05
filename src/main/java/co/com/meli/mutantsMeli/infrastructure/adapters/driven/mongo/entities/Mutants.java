package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities;

import co.com.meli.mutantsMeli.domain.utils.Generated;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Generated
@Data
@Builder(toBuilder = true)
public class Mutants {
    @Id
    private String id;
    private String [] dna;
    private boolean isMutant;
}
