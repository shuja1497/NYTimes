package com.droidco.nytimes.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.droidco.nytimes.R
import com.droidco.nytimes.databinding.FragmentArticleDetailsBinding
import com.droidco.nytimes.viewmodel.ArticleDetailViewModel
import timber.log.Timber

class ArticleDetailsFragment : BaseFragment() {

    private val args: ArticleDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentArticleDetailsBinding
    private val articleDetailViewModel: ArticleDetailViewModel by viewModels()

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val articleId = args.articleId

        Timber.d("ARTICLE ID >>> $articleId")
        articleDetailViewModel.getArticle(articleId)

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