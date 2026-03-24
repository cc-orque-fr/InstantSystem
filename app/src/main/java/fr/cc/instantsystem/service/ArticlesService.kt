package com.example.decath.service

import fr.cc.instantsystem.models.Article
import retrofit2.http.GET

interface ArticlesService {

    @GET("")
    suspend fun getStoreItem() : List<Article>

}