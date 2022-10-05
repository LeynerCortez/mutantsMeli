package co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out;

import co.com.meli.mutantsMeli.domain.utils.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Generated
@Data
@Builder(toBuilder = true)
public class ResponseStats {
    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;
    @JsonProperty("count_human_dna")
    private Long countHumanDna;
    @JsonProperty("ratio")
    private Double ratio;
}
