package com.wishlist.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String link;
    private String loja;
    private BigDecimal precoAtual;
    private String imagemUrl;
    private LocalDateTime ultimaAtualizacao;
    private Long listaId;
    private String listaNome;

}
