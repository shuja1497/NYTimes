package com.droidco.nytimes.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidco.nytimes.R
import com.droidco.nytimes.databinding.ArticlesListFragmentBinding
import com.droidco.nytimes.viewmodel.ArticlesListViewModel
import com.droidco.nytimes.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class ArticlesListFragment : Fragment() {

    companion object {
        fun newInstance() = ArticlesListFragment()

        private const val TAG = "ArticlesListFragment"
    }

    private lateinit var viewModel: ArticlesListViewModel
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
        Log.d(TAG, "onCreate: SECTION: $section")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated: SECTION: $section")

        setupViewModel()
        setupUI()
        setupObservers()
        getArticlesData()
    }

    private fun getArticlesData() {
        lifecycleScope.launch {
            viewModel.getArticles(section)
        }
    }

    private fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.errorTv.visibility = View.GONE
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.GONE
                binding.articlesRv.visibility = View.GONE
                binding.errorTv.visibility = View.VISIBLE
            }
        })

        viewModel.articlesList.observe(viewLifecycleOwner, Observer {
            binding.articlesRv.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            adapter.addItems(it)
        })
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(this.context)
        binding.articlesRv.layoutManager = layoutManager
        binding.articlesRv.adapter = adapter
    }

    private fun setupViewModel() {
        //        viewModel = ViewModelProvider(this).get(ArticlesListViewModel::class.java)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        )
            .get(ArticlesListViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: SECTION: $section")

    }

}