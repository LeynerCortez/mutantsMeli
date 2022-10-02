package co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

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
