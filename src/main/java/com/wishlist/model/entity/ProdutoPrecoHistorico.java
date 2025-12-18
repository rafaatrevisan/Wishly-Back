package com.wishlist.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto_preco_historico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoPrecoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private String loja;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @PrePersist
    public void prePersist() {
        this.dataColeta = LocalDateTime.now();
    }
}
