package dev.wxlf.numbersapi_android_client.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun writeFact(fact: String)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showError(error: String)
}