package fr.cc.instantsystem.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cc.instantsystem.models.Article
import fr.cc.instantsystem.models.CategoryItemsByName
import fr.cc.instantsystem.repositerie.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class LatestNewsUiState {
    object LoadingCatgories : LatestNewsUiState()
    data class SuccessCategory(val categories: List<CategoryItemsByName>) : LatestNewsUiState()
    data class SuccessArticle(val categories: List<Article>) : LatestNewsUiState()

    data class ErrorCategories(val message: String) : LatestNewsUiState()
    data class ErrorArticles(val message: String) : LatestNewsUiState()
}

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow<LatestNewsUiState>(LatestNewsUiState.LoadingCatgories)
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    init {
        viewModelScope.launch {
            runCatching {
                val resposne = repository.getCategoryItems()
                val categoriesType = resposne.map { it.category }.distinct()
                val finalResponse = mutableListOf<CategoryItemsByName>()
                categoriesType.forEach { it ->
                    val allCategoriesByType =
                        resposne.filter { category -> category.category == it }
                    if (allCategoriesByType.isNotEmpty()) {
                        finalResponse.add(CategoryItemsByName(it, allCategoriesByType))
                    }
                }
                if (finalResponse.isNotEmpty()) {
                    _uiState.value = LatestNewsUiState.SuccessCategory(finalResponse)
                } else {
                    // TODO: 404 error 
                }

            }.onFailure {
                _uiState.value = LatestNewsUiState.ErrorCategories(it.toString())
            }
        }

    }

    fun getAllArticleForThisCategory(category: String, date: String) {

        viewModelScope.launch {
            runCatching {
                _uiState.value = LatestNewsUiState.SuccessArticle(repository.getArticleItems())

            }.onFailure {
                _uiState.value = LatestNewsUiState.ErrorArticles(it.toString())
            }

        }
    }


}