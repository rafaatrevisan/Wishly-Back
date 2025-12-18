package com.wishlist.repository;

import com.wishlist.model.entity.ProdutoPrecoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoPrecoHistoricoRepository
        extends JpaRepository<ProdutoPrecoHistorico, Long> {

    Optional<ProdutoPrecoHistorico>
    findTopByProdutoIdOrderByDataColetaDesc(Long produtoId);
}
