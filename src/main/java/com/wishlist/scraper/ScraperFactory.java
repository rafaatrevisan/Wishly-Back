package com.wishlist.scraper;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ScraperFactory {

    private final List<PriceScraper> scrapers;

    public ScraperFactory(List<PriceScraper> scrapers) {
        this.scrapers = scrapers;
    }

    public PriceScraper getScraper(String url) {
        return scrapers.stream()
                .filter(scraper -> scraper.supports(url))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Nenhum scraper disponível para a URL: " + url
                        )
                );
    }
}
