package dev.wxlf.numbersapi_android_client.presenters

import dev.wxlf.numbersapi_android_client.remote.NumbersApi
import dev.wxlf.numbersapi_android_client.views.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    private val TAG: String = MainPresenter::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun fetchFact(num: Int?, numbersApi: NumbersApi) {
        if (num == null) {
            viewState.showError("Введите число!")
        } else {
            compositeDisposable.add(numbersApi.getTriviaFact(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.writeFact(it)
                }, {
                    viewState.showError(it.localizedMessage as String)
                })
            )
        }
    }
}