package com.droidco.nytimes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidco.nytimes.R
import com.droidco.nytimes.databinding.ArticlesListFragmentBinding
import com.droidco.nytimes.model.data.ArticleResponse
import com.droidco.nytimes.model.repository.ArticlesRepository
import com.droidco.nytimes.viewmodel.ArticlesListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

class ArticlesListFragment : BaseFragment() {

    companion object {
        fun newInstance() = ArticlesListFragment()
    }

    private var viewModel = ArticlesListViewModel(ArticlesRepository())
    private val args: ArticlesListFragmentArgs by navArgs()
    private var section: String = ""
    private val adapter = ArticleAdapter(arrayListOf())
    private lateinit var binding: ArticlesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.articles_list_fragment, container, false
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        section = args.section
        Timber.d("onCreate: SECTION: $section")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Timber.d("onActivityCreated: SECTION: $section")

        setupUI()
        getArticlesData()
    }

    private fun getArticlesData() {
        binding.progressBar.visibility = View.VISIBLE

        subscribe(
            viewModel.getArticles(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received articles in view ${it.articles.size}")
                    showArticles(it)
                }, {
                    Timber.w(it)
                    showError()
                })
        )
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(this.context)
        binding.articlesRv.layoutManager = layoutManager
        binding.articlesRv.adapter = adapter
    }

    private fun showArticles(articleResponse: ArticleResponse) {

        if (articleResponse.error == null) {
            binding.articlesRv.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            adapter.addItems(articleResponse.articles)
        } else if (articleResponse.error is ConnectException || articleResponse.error is UnknownHostException) {
            Timber.d("Connection error.")
        } else {
            showError()
        }
    }

    private fun showError() {
        binding.progressBar.visibility = View.GONE
        binding.articlesRv.visibility = View.GONE
        binding.errorTv.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy: SECTION: $section")
    }

}