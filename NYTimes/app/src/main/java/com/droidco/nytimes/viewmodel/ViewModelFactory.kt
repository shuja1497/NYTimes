package com.droidco.nytimes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidco.nytimes.model.repository.ArticlesRepository

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticlesListViewModel::class.java)) {
            return ArticlesListViewModel(
                ArticlesRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

