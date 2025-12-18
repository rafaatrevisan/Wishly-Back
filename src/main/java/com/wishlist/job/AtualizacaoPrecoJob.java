package com.wishlist.job;

import com.wishlist.service.ProdutoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AtualizacaoPrecoJob {

    private final ProdutoService produtoService;

    public AtualizacaoPrecoJob(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Executa a cada 6 horas
     */
    @Scheduled(cron = "0 0 */6 * * *")
    public void executarAtualizacaoDePrecos() {

        System.out.println("[JOB] Iniciando atualização automática de preços");

        produtoService.atualizarPrecosAutomaticamente();

        System.out.println("[JOB] Atualização automática finalizada");
    }
}
