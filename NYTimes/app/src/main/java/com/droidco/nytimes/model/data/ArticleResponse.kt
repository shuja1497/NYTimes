package com.droidco.nytimes.model.data

data class ArticleResponse(
    val articles: List<Article>,
    val message: String = "",
    val error: Throwable? = null
) {
}