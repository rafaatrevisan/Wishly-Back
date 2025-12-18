package com.wishlist.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoPrecoHistoricoResponseDTO {

    private BigDecimal preco;
    private LocalDateTime dataColeta;

    public ProdutoPrecoHistoricoResponseDTO(BigDecimal preco, LocalDateTime dataColeta) {
        this.preco = preco;
        this.dataColeta = dataColeta;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }
}
