package com.example.clickingbad.ui.scenes.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import com.example.clickingbad.R
import kotlinx.android.synthetic.main.fragment_game.*

/** onSaveInstanceState Bundle Keys **/
const val KEY_BATCHES_AMOUNT = "batches_amount_key"
const val KEY_BATCHES_RPS_NET = "batches_net_rps_key"
const val KEY_BATCHES_RPS_GROSS = "batches_gross_rps_key"
const val KEY_CASH_AMOUNT = "cash_amount_key"
const val KEY_LAUNDERED_AMOUNT = "laundered_amount_key"
const val KEY_CASH_RPS = "cash_rps_key"

class GameFragment : Fragment(), LifecycleObserver {

    private val viewModel: GameViewModel by viewModels()

    // create adapter, add it as an observer of the viewmodel
    // send list to the adapter and notify changes, so the view is updated
    private var batchesAmount: Long = 0
    private var batchesRpsNet: Long = 0
    private var batchesRpsGross: Long = 0
    private var cashAmount: Long = 0
    private var launderedAmount: Long = 0
    private var cashRps: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCook.setOnClickListener {
            onButtonCookClicked()
        }

        buttonSell.setOnClickListener {
            onButtonSellClicked()
        }

        // If there is a savedInstanceState bundle, then you're "restarting" the activity
        // If there isn't a bundle, then it's a "fresh" start
        if (savedInstanceState != null) {
            // Get all the game state information from the bundle, set it
            batchesAmount = savedInstanceState.getLong(KEY_BATCHES_AMOUNT, 0)
            batchesRpsNet = savedInstanceState.getLong(KEY_BATCHES_RPS_NET, 0)
            batchesRpsGross = savedInstanceState.getLong(KEY_BATCHES_RPS_GROSS, 0)
            cashAmount = savedInstanceState.getLong(KEY_CASH_AMOUNT, 0)
            launderedAmount = savedInstanceState.getLong(KEY_LAUNDERED_AMOUNT, 0)
            cashRps = savedInstanceState.getLong(KEY_CASH_RPS, 0)
        }

        batchesStored.text = batchesAmount.toString()
        batchesNet.text = batchesRpsNet.toString()
        batchesGross.text = batchesRpsGross.toString()
        moneyStored.text = cashAmount.toString()
        moneyLaundered.text = launderedAmount.toString()
        moneyPerSecond.text = cashRps.toString()
    }

    // Cook button clicked
    private fun onButtonCookClicked() {
        TODO("Not yet implemented")
    }

    // Sell button clicked
    private fun onButtonSellClicked() {
        TODO("Not yet implemented")
    }

    /**
     * Called when the user navigates away from the app but might come back
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(KEY_BATCHES_AMOUNT, 0)
        outState.putLong(KEY_BATCHES_RPS_NET, 0)
        outState.putLong(KEY_BATCHES_RPS_GROSS, 0)
        outState.putLong(KEY_CASH_AMOUNT, 0)
        outState.putLong(KEY_LAUNDERED_AMOUNT, 0)
        outState.putLong(KEY_CASH_RPS, 0)

        super.onSaveInstanceState(outState)
    }

}
