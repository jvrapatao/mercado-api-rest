package jvr.apirest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(
    
    @NotNull
    Long id,

    String nome,

    Categoria categoria,

    int quantidade,

    LocalDate validade,
    
    String marca
) {}
