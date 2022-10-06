package co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.in;

import co.com.meli.mutantsMeli.config.Generated;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Generated
@Data
public class RequestMutant{
    @NotNull(message = "DNA no puede ser nulo")
    //@NotBlank(message = "DNA no puede estar vacio")
    private String [] dna;
}
