package dev.wxlf.numbersapi_android_client.presenters

import android.os.Build
import androidx.annotation.RequiresApi
import dev.wxlf.numbersapi_android_client.remote.NumbersApi
import dev.wxlf.numbersapi_android_client.views.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    private val TAG: String = MainPresenter::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()
    private val typesList: List<String> = listOf<String>("trivia", "math", "year", "date")

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun fetchFact(num: String, type: Int, numbersApi: NumbersApi) {
        if (type == 3) {
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
            val date = LocalDate.parse("2022/$num", formatter).atStartOfDay()
            compositeDisposable.add(numbersApi.getDateFact(date.month.value, date.dayOfMonth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.writeFact(it)
                }, {
                    viewState.showError(it.localizedMessage as String)
                })
            )
        } else {
            compositeDisposable.add(numbersApi.getFact(num, typesList[type])
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