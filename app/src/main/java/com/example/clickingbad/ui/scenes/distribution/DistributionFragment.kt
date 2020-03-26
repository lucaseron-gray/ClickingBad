package com.example.clickingbad.ui.scenes.distribution

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickingbad.R
import com.example.clickingbad.storage.ClickingBadDatabase
import com.example.clickingbad.ui.adapters.DistributionListAdapter
import com.example.clickingbad.utils.fetchJson
import kotlinx.android.synthetic.main.fragment_distribution.*

class DistributionFragment : Fragment() {

    private val viewModel: DistributionViewModel by viewModels()
    private val distributionAdapter = DistributionListAdapter() // é o seu madruga

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_distribution, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // primeiro
        rv_distribution.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = distributionAdapter
        }

        // asynchronous
        viewModel.distributionList.observe(viewLifecycleOwner, Observer {
            distributionAdapter.submitList(it)
        })
    }
}
