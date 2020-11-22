package com.droidco.nytimes.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.droidco.nytimes.R

class ArticlesListFragment : Fragment() {

    companion object {
        fun newInstance() = ArticlesListFragment()

        private const val TAG = "ArticlesListFragment"
    }

    private lateinit var viewModel: ArticlesListViewModel
    private val args: ArticlesListFragmentArgs by navArgs()
    private var section: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.articles_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        section = args.section
        Log.d(TAG, "onCreate: SECTION: $section")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticlesListViewModel::class.java)

        Log.d(TAG, "onActivityCreated: SECTION: $section")

        viewModel.text.observe(viewLifecycleOwner, Observer {
//            listte .text = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: SECTION: $section")

    }

}