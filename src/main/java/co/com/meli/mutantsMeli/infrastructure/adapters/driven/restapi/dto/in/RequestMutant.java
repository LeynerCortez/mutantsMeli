package co.com.meli.mutantsMeli.infrastructure.adapters.driven.restapi.dto.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestMutant {
    @NotNull(message = "DNA no puede ser nulo")
    @NotBlank(message = "DNA no puede estar vacio")
    private String [] dna;
}
