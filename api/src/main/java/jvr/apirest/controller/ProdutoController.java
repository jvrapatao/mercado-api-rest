package jvr.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jvr.apirest.dto.DadosAtualizarProduto;
import jvr.apirest.dto.DadosAtualizarResponseEntity;
import jvr.apirest.dto.DadosCadastroProduto;
import jvr.apirest.dto.DadosListagemProduto;
import jvr.apirest.produtoEntidade.Produto;
import jvr.apirest.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosAtualizarResponseEntity> cadastrarProduto (@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = new Produto(dados);
        repository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosAtualizarResponseEntity(produto));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemProduto>> listarProduto () {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemProduto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosAtualizarResponseEntity> listarProdutoPorId (@PathVariable Long id) {
        var produtoId = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosAtualizarResponseEntity(produtoId));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosAtualizarResponseEntity> atualizarProduto (@RequestBody @Valid DadosAtualizarProduto dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.atualizarProduto(dados);
        return ResponseEntity.ok(new DadosAtualizarResponseEntity(produto));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void>  deletarProduto (@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativarProduto (@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.inativarProduto();
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativarProduto (@PathVariable Long id ) {
        var produto = repository.getReferenceById(id);
        produto.ativarProduto();
        return ResponseEntity.noContent().build();
    }
}