package jvr.apirest.dto;

import java.time.LocalDate;

import jvr.apirest.produtoEntidade.Produto;

public record DadosAtualizarResponseEntity(
    Long id,
    String nome,
    Categoria categoria,
    int quantidade,
    LocalDate validade,
    String marca,
    Boolean ativo) {

        public DadosAtualizarResponseEntity (Produto produto) {
            this(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getQuantidade(),
                produto.getValidade(),
                produto.getMarca(),
                produto.getAtivo());
        }
}