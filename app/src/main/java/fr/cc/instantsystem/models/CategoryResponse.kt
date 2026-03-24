package fr.cc.instantsystem.models

data class CategoryResponse(
    val sources: List<SourceCategory>,
    val status: String
)