package com.maider.article.infraestructure.mappers;

import com.maider.article.domain.entities.dto.FilterDTO;
import com.maider.article.domain.entities.ArticleFilter;
import org.springframework.stereotype.Component;

@Component
public class ArticleFilterMapper {
    public ArticleFilter toArticleFilter(FilterDTO filterdto) {
        return new ArticleFilter(filterdto.getType(),
                                filterdto.getSizeLessThan(),
                                filterdto.getSizeGreaterThan(),
                                filterdto.getMaterial(),
                                filterdto.getBrand(),
                                filterdto.getPriceLessThan(),
                                filterdto.getPriceGreaterThan());
    }
}
