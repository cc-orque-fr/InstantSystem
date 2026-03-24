package fr.cc.instantsystem.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cc.instantsystem.models.Article
import fr.cc.instantsystem.models.SourceCategory
import fr.cc.instantsystem.repositerie.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class LatestNewsUiState {
    object Loading : LatestNewsUiState()
    data class SuccessCategory(val categories : List<SourceCategory>) : LatestNewsUiState()
    data class SuccessArticle(val categories : List<Article>) : LatestNewsUiState()

    data class ErrorCategories(val message: String) : LatestNewsUiState()
    data class ErrorArticles(val message: String) : LatestNewsUiState()
}

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow<LatestNewsUiState>(LatestNewsUiState.Loading)
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    init {
        viewModelScope.launch {
            runCatching {
                _uiState.value = LatestNewsUiState.SuccessCategory(repository.getCategoryItems())
            }.onFailure {
                _uiState.value = LatestNewsUiState.ErrorCategories(it.toString())
            }
        }

    }

     fun getAllArticleForThisCategory(category : String,date : String) {

        viewModelScope.launch {
            runCatching {
                _uiState.value = LatestNewsUiState.SuccessArticle(repository.getArticleItems())

            }.onFailure {
                _uiState.value = LatestNewsUiState.ErrorArticles(it.toString())
            }

        }
     }


}