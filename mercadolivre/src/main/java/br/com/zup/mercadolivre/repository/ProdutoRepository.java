package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
