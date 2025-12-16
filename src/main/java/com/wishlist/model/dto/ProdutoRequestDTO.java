package com.wishlist.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    private String nome;
    private String link;
    private String loja;
    private BigDecimal precoAtual;
    private String imagemUrl;
    private Long listaId;

}
