package com.droidco.nytimes.di.component

import com.droidco.nytimes.di.ApplicationScope
import com.droidco.nytimes.di.module.NetworkModule
import com.droidco.nytimes.di.module.SubcomponentsModule
import com.droidco.nytimes.model.repository.ArticlesRepository
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun getArticleRepository(): ArticlesRepository

    fun articleListComponent(): ArticleListFragmentComponent.Factory

//    fun inject(articlesListFragment: ArticlesListFragment)
}