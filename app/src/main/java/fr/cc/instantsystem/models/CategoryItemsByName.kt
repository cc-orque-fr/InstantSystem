package fr.cc.instantsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemsByName (
    val category: String,
    val subItems : List<SourceCategory>
)