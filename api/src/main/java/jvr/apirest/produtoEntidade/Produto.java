package jvr.apirest.produtoEntidade;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jvr.apirest.dto.Categoria;
import jvr.apirest.dto.DadosAtualizarProduto;
import jvr.apirest.dto.DadosCadastroProduto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Produto")
@Entity(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

     @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private int quantidade;
	private LocalDate validade;
    private String marca;
    private Boolean ativo;
    
    public Produto(DadosCadastroProduto dados) {
        this.nome=dados.nome();
        this.categoria=dados.categoria();
        this.quantidade=dados.quantidade();
        this.validade=dados.validade();
        this.marca=dados.marca();
        this.ativo = true;
    }

    public void atualizarProduto(@Valid DadosAtualizarProduto dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
        if (dados.quantidade() != 0) {
            this.quantidade = dados.quantidade();
        }
        if (dados.validade() != null) {
            this.validade = dados.validade();
        }
        if (dados.marca() != null) {
            this.marca = dados.marca();
        }
    }

    public void inativarProduto() {
        this.ativo = false;
    }

    public void ativarProduto() {
        this.ativo = true;
    }
}
