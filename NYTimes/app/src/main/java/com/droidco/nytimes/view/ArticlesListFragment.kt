package com.droidco.nytimes.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.droidco.nytimes.R
import com.droidco.nytimes.viewmodel.ArticlesListViewModel
import com.droidco.nytimes.viewmodel.ViewModelFactory

class ArticlesListFragment : Fragment() {

    companion object {
        fun newInstance() = ArticlesListFragment()

        private const val TAG = "ArticlesListFragment"
    }

    private lateinit var viewModel: ArticlesListViewModel
    private val args: ArticlesListFragmentArgs by navArgs()
    private var section: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_list_fragment, container, false)
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
    }

    private fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
        })

        viewModel.articlesList.observe(viewLifecycleOwner, Observer {
        })
    }

    private fun setupUI() {

    }

    private fun setupViewModel() {
        //        viewModel = ViewModelProvider(this).get(ArticlesListViewModel::class.java)
        viewModel = ViewModelProvider(this,
            ViewModelFactory()
        )
            .get(ArticlesListViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: SECTION: $section")

    }

}