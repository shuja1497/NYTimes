package com.droidco.nytimes.di.module

import com.droidco.nytimes.di.component.ArticleDetailFragmentComponent
import com.droidco.nytimes.di.component.ArticleListFragmentComponent
import dagger.Module

@Module(subcomponents = [ArticleListFragmentComponent::class, ArticleDetailFragmentComponent::class])
class SubcomponentsModule {

}