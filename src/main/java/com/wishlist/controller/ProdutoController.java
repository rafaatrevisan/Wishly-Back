package com.wishlist.controller;

import com.wishlist.model.dto.ProdutoPrecoHistoricoResponseDTO;
import com.wishlist.model.dto.ProdutoRequestDTO;
import com.wishlist.model.dto.ProdutoResponseDTO;
import com.wishlist.model.entity.Produto;
import com.wishlist.service.ProdutoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @PutMapping("/produtos/{id}/atualizar-preco-auto")
    public ProdutoResponseDTO atualizarPrecoAutomatico(@PathVariable Long id) {
        return produtoService.atualizarPrecoAutomatico(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException ex) {
        return Map.of(
                "error", ex.getMessage()
        );
    }

    @PostMapping("/listas/{listaId}/atualizar-precos")
    public List<ProdutoResponseDTO> atualizarPrecosDaLista(
            @PathVariable Long listaId
    ) {
        return produtoService.atualizarPrecosDaLista(listaId);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @RequestBody ProdutoRequestDTO dto
    ) {
        ProdutoResponseDTO response = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/produtos/{produtoId}/historico")
    public List<ProdutoPrecoHistoricoResponseDTO> historicoPreco(
            @PathVariable Long produtoId,
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFim
    ) {
        return produtoService.obterHistoricoPreco(
                produtoId,
                dataInicio.atStartOfDay(),
                dataFim.atTime(23, 59, 59)
        );
    }

}
