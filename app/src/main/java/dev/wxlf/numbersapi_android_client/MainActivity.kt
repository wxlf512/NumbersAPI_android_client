package dev.wxlf.numbersapi_android_client

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView{

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.showFactBtn).setOnClickListener {
            mainPresenter.fetchFact(findViewById<EditText>(R.id.userNum).text.toString().toInt())
        }
    }

    override fun writeFact(fact: String) {
        findViewById<TextView>(R.id.factView).text = fact
    }
}