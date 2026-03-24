package fr.cc.instantsystem.repositerie

import com.example.decath.service.ArticlesService
import fr.cc.instantsystem.models.Article
import fr.cc.instantsystem.models.SourceCategory

class RepositoryImpl(val articlesService: ArticlesService) : Repository {
    override suspend fun getArticleItems(): List<Article> {
        return articlesService.getArticlesItem().articles
    }

    override suspend fun getCategoryItems(): List<SourceCategory> {
        return articlesService.getCategoriesItem().sources
    }


}