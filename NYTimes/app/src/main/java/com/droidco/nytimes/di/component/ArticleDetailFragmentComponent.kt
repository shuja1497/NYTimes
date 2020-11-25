package com.droidco.nytimes.di.component

import com.droidco.nytimes.di.FragmentScope
import com.droidco.nytimes.view.ArticleDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface ArticleDetailFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArticleDetailFragmentComponent
    }

    fun inject(articleDetailsFragment: ArticleDetailsFragment)
}