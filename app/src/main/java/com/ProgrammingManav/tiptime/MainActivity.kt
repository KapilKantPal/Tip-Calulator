package com.ProgrammingManav.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ProgrammingManav.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    /**
     * -  Instead of using "FindViewById()" function ew use binding function which already have a
     *    reference to the all view and we that's why i replace main code with below code
     * -  For Example
     *
     *    1 = Old way with findViewById()
     *       val myButton: Button = findViewById(R.id.my_button)
     *       myButton.text = "A button"
     *
     *    2 = Better way with view binding
     *        val myButton: Button = binding.myButton
     *        myButton.text = "A button"
     *
     *    3 = Best way with view binding and no extra variable
     *        binding.myButton.text = "A button"
     */

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         * here, we have created a reference of the EditText Through binding function
         */
       binding.calculate.setOnClickListener{calculateTip()}
    }

    /**
     * here, we created a function which will call when we press the calculate button
     */
    private fun calculateTip(){
        // here, we stored the text of plainTextInput to StringInThetextField in the form of String
        // convert Text toString because to convert it into Double First it should be converted toString

        val StringInThetextField = binding.costOfServiceEditText.text.toString()
        val Cost = StringInThetextField.toDoubleOrNull()
        if(Cost==null){
            binding.tipResult.text=""
            return
        }
        val SelectedId = binding.tipOption.checkedRadioButtonId
        val tipPercentage: Double = when(SelectedId) {
            R.id.Amazing -> 0.20
            R.id.Good -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage*Cost
        val roundUp = binding.RoundUp.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}