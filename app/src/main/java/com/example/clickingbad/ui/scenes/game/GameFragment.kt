package com.example.clickingbad.ui.scenes.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.clickingbad.R
import com.example.clickingbad.ui.activity.SharedViewModel
import com.example.clickingbad.utils.formatPurity
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

                vBatchPurity.text = formatPurity(it.batchPrice)

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

    override fun onResume() {
        super.onResume()

        Log.i("GameFragment", "onResume Called!")
        sharedViewModel.refreshData()
    }
}
