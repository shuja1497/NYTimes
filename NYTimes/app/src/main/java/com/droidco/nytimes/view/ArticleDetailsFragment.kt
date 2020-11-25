package com.droidco.nytimes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.droidco.nytimes.R
import com.droidco.nytimes.databinding.FragmentArticleDetailsBinding
import com.droidco.nytimes.di.component.ArticleDetailFragmentComponent
import com.droidco.nytimes.init.ApplicationController
import com.droidco.nytimes.viewmodel.ArticleDetailViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ArticleDetailsFragment : BaseFragment() {

    private val args: ArticleDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentArticleDetailsBinding
    lateinit var articleDetailFragmentComponent: ArticleDetailFragmentComponent

    @Inject
    lateinit var articleDetailViewModel: ArticleDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_article_details, container, false
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        articleDetailFragmentComponent =
            ApplicationController.getAppComponent().articleDetailComponent().create()
        articleDetailFragmentComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val articleId = args.articleId

        Timber.d("ARTICLE ID >>> $articleId")
        lifecycleScope.launch {
            articleDetailViewModel.getArticle(articleId)
        }

        observeViewModel()
    }

    private fun observeViewModel() {

        articleDetailViewModel.article.observe(viewLifecycleOwner, Observer {
            Timber.d("ARTICLE >>> $it")

            it?.let {
                binding.article = it
            }
        })
    }
}