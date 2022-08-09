package dev.wxlf.numbersapi_android_client.screens

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import dev.wxlf.numbersapi_android_client.NumbersApp
import dev.wxlf.numbersapi_android_client.presenters.MainPresenter
import dev.wxlf.numbersapi_android_client.views.MainView
import dev.wxlf.numbersapi_android_client.R
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView, AdapterView.OnItemSelectedListener {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var userNumInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNumInput = findViewById<EditText>(R.id.userNum)
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        val randomCheckBox = findViewById<CheckBox>(R.id.randomCheckBox)
        randomCheckBox.setOnClickListener {
            userNumInput.isEnabled = !randomCheckBox.isChecked
        }

        findViewById<Button>(R.id.showFactBtn).setOnClickListener {
            if (randomCheckBox.isChecked) {
                mainPresenter.fetchFact(
                    "random",
                    spinner.selectedItemPosition,
                    (this.application as? NumbersApp)!!.numbersApi
                )
            } else {
                mainPresenter.fetchFact(
                    userNumInput.text.toString(),
                    spinner.selectedItemPosition,
                    (this.application as? NumbersApp)!!.numbersApi
                )
            }
        }
    }

    override fun writeFact(fact: String) {
        findViewById<TextView>(R.id.factView).text = fact
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0?.selectedItemPosition == 3) {
            userNumInput.inputType = InputType.TYPE_CLASS_DATETIME
            userNumInput.hint = getString(R.string.date_hint)
        } else {
            userNumInput.inputType = InputType.TYPE_CLASS_NUMBER
            userNumInput.hint = getString(R.string.number_hint)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}