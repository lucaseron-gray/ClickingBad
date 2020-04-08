package com.example.clickingbad.ui.scenes.upgrades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clickingbad.R
import com.example.clickingbad.ui.adapters.UpgradesListAdapter
import kotlinx.android.synthetic.main.fragment_upgrades.*

class UpgradesFragment : Fragment() {

    private val viewModel: UpgradesViewModel by viewModels()
    private val upgradesAdapter = UpgradesListAdapter() // é o seu madruga

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_upgrades, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // primeiro
        rv_upgrades.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = upgradesAdapter
        }

        // asynchronous
        viewModel.upgradesList.observe(viewLifecycleOwner, Observer {
            upgradesAdapter.submitList(it)
        })
    }

}
