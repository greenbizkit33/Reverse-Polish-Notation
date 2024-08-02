package com.nathanhaze.snapcalculator.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathanhaze.snapcalculator.ui.util.PolishNotationUtil

class TerminalViewModel : ViewModel() {
    private val _terminalOutputList = MutableLiveData<ArrayList<String>>(arrayListOf())

    val terminalOutputList: LiveData<ArrayList<String>>
        get() = _terminalOutputList

    private val _terminalOutputError = MutableLiveData<Exception>(null)

    val terminalOutputError: LiveData<Exception>
        get() = _terminalOutputError


    fun addToStack(userInput: String): Boolean {
        try {
            PolishNotationUtil.isValidInput(userInput)
        } catch (ex: Exception) {
            _terminalOutputError.value = ex
            _terminalOutputError.postValue(ex)
            return false
        }
        var output = PolishNotationUtil.addToPolishNotationStack(userInput)

        output?.let { newValue ->
            stackIt(newValue)
        } ?: run {
            stackIt(userInput)
        }

//
//        if (userInput.isDigitsOnly()) {
//            _terminalOutputList.value?.let { stack ->
//                stackIt(userInput)
//                PolishNotationUtil.addToPolishNotationStack(userInput)
//                return true
//            }
//        }
//
//        PolishNotationUtil.addToPolishNotationStack(userInput)
//        val calculatedNumber = PolishNotationUtil.calculateInput()
//        Log.d("nathanx", "calculated Number: $calculatedNumber")
//        stackIt(calculatedNumber.toString())
//
//        Log.d("nathanx", "input $userInput")

        return true
    }

    fun stackIt(input: String) {
        _terminalOutputList.value?.add(input)
        _terminalOutputList.postValue(_terminalOutputList.value)
    }


    class AlphabeticSymbol : Exception("")
}