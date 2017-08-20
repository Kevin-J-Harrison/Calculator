package com.example.kharrison.my_application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CalculatorActivity : AppCompatActivity() {

   private val calcView : View by lazy {
      findViewById(R.id.calculator_layout)
   }

   override fun onCreate(savedInstanceState : Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_calculator)
   }

   override fun onResume() {
      super.onResume()
      CalculatorController(calcView)
   }
}
