package com.paoloprodossimolopes.gastoviagem

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paoloprodossimolopes.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
    }

    private fun configureLayout() {
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        configureButtonActionBinder()

        // android puro para cessar elemento sem binding
        //val label = findViewById<TextView>(R.id.text_total_ammount)
    }

    private fun configureButtonActionBinder() {
        /*
        //Lambda
        bind.buttonCalculate.setOnClickListener { bind.textTotalAmmount.text = "R$ 32,00" }

        // Classe anonima:
        bind.buttonCalculate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }
        })
        */
        bind.buttonCalculate.setOnClickListener(this) //if implements `View.OnClickListener`
    }

    override fun onClick(view: View?) {
        if (view == bind.buttonCalculate) onCalculateButtonActionHandler()
    }

    private fun onCalculateButtonActionHandler() {
        val distance = getValueIn(bind.editDistancy)
        val price = getValueIn(bind.editPrice)
        val autonomy = getValueIn(bind.editAutonomy)

        if (autonomy <= 0) {
            bind.editAutonomy.setTextColor(getColor(R.color.red))
            Toast.makeText(this, "A autnomia deve ser maior que 0 (zero)!", Toast.LENGTH_SHORT).show()
            //Toast.makeText(applicationContext, "fui clicado!", Toast.LENGTH_SHORT).show()
        } else {
            bind.editAutonomy.setTextColor(getColor(R.color.black))
            val total = (distance * price) / autonomy
            bind.textTotalAmmount.text = convertToCurrenty(total)
        }
    }

    private fun getValueIn(editText: EditText): Double = editText.text.toString().toDoubleOrNull() ?: 0.0
    private fun convertToCurrenty(value: Double): String = "R$ ${"%.2f".format(value)}".replace(".", ",")

}