package jvr.apirest.dto;

import java.time.LocalDate;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProduto(

    @NotBlank
    String nome,

    @Enumerated
    Categoria categoria,

    int quantidade,

    @Future
	LocalDate validade,

    @NotBlank
    String marca
) {}
