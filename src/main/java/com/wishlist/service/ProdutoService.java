package com.wishlist.service;

import com.wishlist.model.dto.ProdutoRequestDTO;
import com.wishlist.model.dto.ProdutoResponseDTO;
import com.wishlist.model.entity.Lista;
import com.wishlist.model.entity.Produto;
import com.wishlist.repository.ListaRepository;
import com.wishlist.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ListaRepository listaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, ListaRepository listaRepository) {
        this.produtoRepository = produtoRepository;
        this.listaRepository = listaRepository;
    }

    public List<Produto> listarPorLista(Long listaId) {
        return produtoRepository.findByListaId(listaId);
    }

    public ProdutoResponseDTO adicionar(ProdutoRequestDTO dto) {

        Lista lista = listaRepository.findById(dto.getListaId())
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setLink(dto.getLink());
        produto.setLoja(dto.getLoja());
        produto.setPrecoAtual(dto.getPrecoAtual());
        produto.setImagemUrl(dto.getImagemUrl());
        produto.setLista(lista);
        produto.setUltimaAtualizacao(LocalDateTime.now());

        Produto salvo = produtoRepository.save(produto);

        return mapToResponseDTO(salvo);
    }

    private ProdutoResponseDTO mapToResponseDTO(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setLink(produto.getLink());
        dto.setLoja(produto.getLoja());
        dto.setPrecoAtual(produto.getPrecoAtual());
        dto.setImagemUrl(produto.getImagemUrl());
        dto.setUltimaAtualizacao(produto.getUltimaAtualizacao());
        dto.setListaId(produto.getLista().getId());
        dto.setListaNome(produto.getLista().getNome());
        return dto;
    }

    public void remover(Long produtoId) {
        produtoRepository.deleteById(produtoId);
    }

    public BigDecimal totalDaLista(Long listaId) {
        return produtoRepository.calcularTotalPorLista(listaId);
    }

    public ProdutoResponseDTO atualizarPreco(
            Long produtoId,
            BigDecimal novoPreco
    ) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setPrecoAtual(novoPreco);
        produto.setUltimaAtualizacao(LocalDateTime.now());

        Produto salvo = produtoRepository.save(produto);

        return mapToResponseDTO(salvo);
    }

}
