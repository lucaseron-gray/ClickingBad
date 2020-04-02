package com.example.clickingbad.ui.scenes.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.clickingbad.R
import com.example.clickingbad.ui.activity.SharedViewModel
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    // passando 'activityViewModels' pra que o viewModel não seja recriado na navegação
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game, container,false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observa model e atribui valores
        sharedViewModel.livePlayerData.observe(viewLifecycleOwner, Observer {
            it?.let {

                vBatchPurity.text = "Batches (${when (it.batchPrice) {
                    1 -> "Deadly"
                    2 -> "Dangerous"
                    4 -> "Unhealthy"
                    6 -> "Cloudy"
                    10 -> "Poor"
                    13 -> "Average"
                    16 -> "Good"
                    20 -> "Crystal"
                    25 -> "Blue Gold"
                    50 -> "Blue Platinum"
                    100 -> "FDA Approved Additive"
                    159 -> "Atomically Perfect"
                    211 -> "Holy"
                    300 -> "Angelic"
                    1000 -> "Nectar of The Gods"
                    else -> "Undefined"
                }})"

                vBatchAmount.text = it.batchAmount.toString()
                vBatchRpsNet.text = "${it.batchRpsNet}/s (net)"
                vBatchRpsGross.text = "${it.batchRpsGross}/s (gross)"

                vBatchPrice.text = "Cash ($${it.batchPrice}.00 each)"
                vCashAmount.text = "$${it.cashAmount}"
                vCashLaundered.text = "$${it.cashLaundered} laundered"
                vCashRps.text = "$${it.cashRps}/s"

            }
        })

        // ao click, muda valor dentro do viewModel, que tá sendo observado em cima ^
        buttonCook.setOnClickListener {
            sharedViewModel.onButtonCookClicked()
        }
        buttonSell.setOnClickListener {
            sharedViewModel.onButtonSellClicked()
        }
    }
}
