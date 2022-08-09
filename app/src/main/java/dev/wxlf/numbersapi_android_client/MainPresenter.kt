package dev.wxlf.numbersapi_android_client

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {
    private val TAG: String = MainPresenter::class.java.simpleName

    fun fetchFact(num: Int) {
        viewState.writeFact("${num}")
    }
}