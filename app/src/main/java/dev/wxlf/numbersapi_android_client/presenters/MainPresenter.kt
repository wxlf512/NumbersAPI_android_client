package dev.wxlf.numbersapi_android_client.presenters

import dev.wxlf.numbersapi_android_client.remote.NumbersApi
import dev.wxlf.numbersapi_android_client.views.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val TAG: String = MainPresenter::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()
    private val typesList: List<String> = listOf<String>("trivia", "math", "year", "date")

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun fetchFact(num: String, type: Int, numbersApi: NumbersApi) {
        if (type == 3 && num != "random") {
            val date = num.split("/")
            compositeDisposable.add(
                numbersApi.getDateFact(date[0].toInt(), date[1].toInt())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        viewState.writeFact(it)
                    }, {
                        viewState.showError(it.localizedMessage as String)
                    })
            )
        } else {
            compositeDisposable.add(
                numbersApi.getFact(num, typesList[type])
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