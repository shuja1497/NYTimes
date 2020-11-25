package com.droidco.nytimes.di.component

import com.droidco.nytimes.di.FragmentScope
import com.droidco.nytimes.view.ArticlesListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface ArticleListFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArticleListFragmentComponent
    }

    fun inject(articlesListFragment: ArticlesListFragment)
}