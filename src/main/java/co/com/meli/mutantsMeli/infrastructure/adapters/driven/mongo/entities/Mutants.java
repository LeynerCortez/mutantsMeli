package co.com.meli.mutantsMeli.infrastructure.adapters.driven.mongo.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class Mutants {
    @Id
    private String id;
    private String [] dna;
    private boolean isMutant;
}
