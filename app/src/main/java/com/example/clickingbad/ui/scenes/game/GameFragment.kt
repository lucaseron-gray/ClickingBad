package com.example.clickingbad.ui.scenes.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import com.example.clickingbad.R
import com.example.clickingbad.ui.activity.SharedViewModel
import kotlinx.android.synthetic.main.fragment_game.*

/** onSaveInstanceState Bundle Keys **/
//const val KEY_BATCHES_AMOUNT = "batches_amount_key"

class GameFragment : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    private var bAmount: Long? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            observeData(sharedViewModel)
        }

        buttonCook.setOnClickListener {
            gameViewModel.onButtonCookClicked()
        }

        buttonSell.setOnClickListener {
            gameViewModel.onButtonSellClicked()
        }

        batchesStored.text = bAmount.toString()
    }

    private fun observeData(viewModel: SharedViewModel) {
        viewModel.dataList.observe(viewLifecycleOwner, Observer {
            it?.let {
                moneyBatchValue.text = "Cash Money ($"+it.batchPrice+" ea)"
            }
        })
    }

    /**
     * Called when the user navigates away from the app but might come back
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        outState.putLong(KEY_BATCHES_AMOUNT, bAmount!!)
    }

}
