package com.wishlist.controller;

import com.wishlist.model.dto.ProdutoRequestDTO;
import com.wishlist.model.dto.ProdutoResponseDTO;
import com.wishlist.model.entity.Produto;
import com.wishlist.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/listas/{listaId}/produtos")
    public List<Produto> listar(@PathVariable Long listaId) {
        return produtoService.listarPorLista(listaId);
    }

    @PostMapping("/produtos")
    public ProdutoResponseDTO adicionar(@RequestBody ProdutoRequestDTO dto) {
        return produtoService.adicionar(dto);
    }

    @DeleteMapping("/produtos/{id}")
    public void remover(@PathVariable Long id) {
        produtoService.remover(id);
    }

    @GetMapping("/listas/{listaId}/total")
    public Map<String, BigDecimal> total(@PathVariable Long listaId) {
        return Map.of(
                "total", produtoService.totalDaLista(listaId)
        );
    }

    @PutMapping("/produtos/{id}/atualizar-preco")
    public ProdutoResponseDTO atualizarPreco(
            @PathVariable Long id,
            @RequestBody Map<String, BigDecimal> body
    ) {
        return produtoService.atualizarPreco(id, body.get("preco"));
    }

}
