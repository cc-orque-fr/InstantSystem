package fr.cc.instantsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)