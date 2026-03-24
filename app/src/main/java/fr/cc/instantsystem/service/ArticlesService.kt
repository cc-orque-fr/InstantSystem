package com.example.decath.service

import fr.cc.instantsystem.models.ArticlesResponse
import fr.cc.instantsystem.models.CategoryResponse
import retrofit2.http.GET

interface ArticlesService {

    @GET("")
    suspend fun getArticlesItem(): ArticlesResponse

    suspend fun getCategoriesItem(): CategoryResponse

}