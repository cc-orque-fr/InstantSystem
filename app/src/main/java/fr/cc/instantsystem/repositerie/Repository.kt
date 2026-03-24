package fr.cc.instantsystem.repositerie

import fr.cc.instantsystem.models.Article
import fr.cc.instantsystem.models.SourceCategory

interface Repository {
    suspend fun getArticleItems(): List<Article>

    suspend fun getCategoryItems(): List<SourceCategory>
}