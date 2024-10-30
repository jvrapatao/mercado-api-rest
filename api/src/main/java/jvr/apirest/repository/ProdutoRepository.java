package jvr.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jvr.apirest.produtoEntidade.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByAtivoTrue();
    

}
