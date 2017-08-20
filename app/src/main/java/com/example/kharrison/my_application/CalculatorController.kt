package com.example.kharrison.my_application

import android.view.View
import com.example.kharrison.my_application.CalculatorApp.Companion.ADDITION
import com.example.kharrison.my_application.CalculatorApp.Companion.DIVISION
import com.example.kharrison.my_application.CalculatorApp.Companion.MULTIPLICATION
import com.example.kharrison.my_application.CalculatorApp.Companion.SUBTRACTION
import kotlinx.android.synthetic.main.activity_calculator.view.*
import kotlinx.android.synthetic.main.number_pad_layout.view.*
import kotlinx.android.synthetic.main.opperations_layout.view.*


class CalculatorController(val view : View) {

   private var inputString = ""
   private var outputString = ""

   private var isOperationSelected = false
   private var selectedOperator = 0

   private var leftHandSide = ""
   private var rightHandSide = ""
   private var result = 0

   private val mathHelper : CalcFunctions by lazy {
      CalcFunctions()
   }

   init {
      with(view) {
         one.setOnClickListener { clickNumber("1") }
         two.setOnClickListener { clickNumber("2") }
         three.setOnClickListener { clickNumber("3") }
         four.setOnClickListener { clickNumber("4") }
         five.setOnClickListener { clickNumber("5") }
         six.setOnClickListener { clickNumber("6") }
         seven.setOnClickListener { clickNumber("7") }
         eight.setOnClickListener { clickNumber("8") }
         nine.setOnClickListener { clickNumber("9") }
         zero.setOnClickListener { clickNumber("0") }
         add.setOnClickListener { clickOperator(ADDITION) }
         subtract.setOnClickListener { clickOperator(SUBTRACTION) }
         multiply.setOnClickListener { clickOperator(MULTIPLICATION) }
         divide.setOnClickListener { clickOperator(DIVISION) }
         equals.setOnClickListener { calculate() }
         clear.setOnClickListener { clearInput() }
      }
      invalidateControls()
   }

   private fun invalidateControls() {
      val isEnabled = (!leftHandSide.isEmpty() || result != 0) && !isOperationSelected

      view.add.isClickable = isEnabled
      view.subtract.isClickable = isEnabled
      view.divide.isClickable = isEnabled
      view.multiply.isClickable = isEnabled
   }

   private fun updateText() {
      view.input.text = inputString
      view.answer.text = outputString
   }

   private fun updateOutPutString() {
      leftHandSide = ""
      rightHandSide = ""
      inputString = ""
      updateText()
   }

   private fun addToInputString(input : String) {
      inputString += input
      updateText()
   }

   private fun clickNumber(num : String) {
      if (!isOperationSelected) {
         leftHandSide += num
      } else {
         rightHandSide += num
      }
      addToInputString(num)
      invalidateControls()
   }

   private fun clickOperator(operator : Int) {
      isOperationSelected = true
      when (operator) {
         ADDITION -> {
            selectedOperator = ADDITION
            addToInputString(" + ")
         }
         SUBTRACTION -> {
            selectedOperator = SUBTRACTION
            addToInputString(" - ")
         }
         MULTIPLICATION -> {
            selectedOperator = MULTIPLICATION
            addToInputString(" * ")
         }
         DIVISION -> {
            selectedOperator = DIVISION
            addToInputString(" / ")
         }
      }
      invalidateControls()
   }

   private fun calculate() {
      isOperationSelected = false
      val leftNum = if (leftHandSide.isEmpty()) result else leftHandSide.toInt()
      val rightNum = if (rightHandSide.isEmpty()) result else rightHandSide.toInt()
      when (selectedOperator) {
         ADDITION -> add(leftNum, rightNum)
         SUBTRACTION -> subtract(leftNum, rightNum)
         MULTIPLICATION -> multiply(leftNum, rightNum)
         DIVISION -> division(leftNum, rightNum)
      }

   }

   private fun add(leftNum : Int, rightNum : Int) {
      result = mathHelper.add(leftNum, rightNum)
      outputString = result.toString()
      updateOutPutString()
   }

   private fun subtract(leftNum : Int, rightNum : Int) {
      result = mathHelper.subtract(leftNum, rightNum)
      outputString = result.toString()
      updateOutPutString()
   }

   private fun multiply(leftNum : Int, rightNum : Int) {
      result = mathHelper.multiply(leftNum, rightNum)
      outputString = result.toString()
      updateOutPutString()
   }


   private fun division(leftNum : Int, rightNum : Int) {
      if (rightNum == 0) {
         clearInput()
         outputString = "ERROR"
      } else {
         result = mathHelper.divide(leftNum, rightNum)
         outputString = result.toString()
      }
      updateOutPutString()
   }

   private fun clearInput() : Boolean {
      inputString = ""
      outputString = ""
      leftHandSide = ""
      rightHandSide = ""
      result = 0
      updateText()
      return true
   }
}