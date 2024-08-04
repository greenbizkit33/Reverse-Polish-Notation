package com.nathanhaze.snapcalculator.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathanhaze.snapcalculator.ui.util.PolishNotationUtil

class TerminalViewModel : ViewModel() {
    private val _terminalOutputList = MutableLiveData<ArrayList<String>>(arrayListOf())

    val terminalOutputList: LiveData<ArrayList<String>>
        get() = _terminalOutputList

    private val _terminalOutputError = MutableLiveData<Exception?>(null)

    val terminalOutputError: LiveData<Exception?>
        get() = _terminalOutputError


    fun addToStack(userInput: String): Boolean {
        var output: String?
        try {
            PolishNotationUtil.isValidInput(userInput)
            output = PolishNotationUtil.addToPolishNotationStack(userInput)
        } catch (ex: Exception) {
            _terminalOutputError.value = ex
            _terminalOutputError.postValue(ex)
            return false
        }

        _terminalOutputError.value = null
        _terminalOutputError.postValue(null)

        output?.let { newValue ->
            stackIt(newValue)
        } ?: run {
            stackIt(userInput)
        }

        return true
    }

    fun clearStacks() {
        PolishNotationUtil.clearStack()
        _terminalOutputList.value?.clear()
        _terminalOutputList.postValue(_terminalOutputList.value)
    }

    fun stackIt(input: String) {
        _terminalOutputList.value?.add(input)
        _terminalOutputList.postValue(_terminalOutputList.value)
    }

    fun displayStack() {
        _terminalOutputList.value?.add("---- Stack ----")
        val stack = PolishNotationUtil.getStack()
        for (item in stack) {
            _terminalOutputList.value?.add(item.toString())
        }
        _terminalOutputList.value?.add("---- Done ----")
        _terminalOutputList.postValue(_terminalOutputList.value)
    }

    fun popStack() {
        val popStackNumber = PolishNotationUtil.popStack()
        popStackNumber?.let { _terminalOutputList.value?.add("Removed $popStackNumber ") }
            ?: _terminalOutputList.value?.add("Nothing to pop")
        _terminalOutputList.postValue(_terminalOutputList.value)
    }


    class AlphabeticSymbol : Exception("")
}