package com.nathanhaze.snapcalculator.ui.util

import android.util.Log
import androidx.core.text.isDigitsOnly
import java.util.regex.Pattern

object PolishNotationUtil {

    private val stack = ArrayDeque<Float>()

    fun clearStack() {
        stack.clear()
    }

    fun getStack(): ArrayDeque<Float> {
        return stack;
    }

    fun popStack(): Float? {
        return stack.removeLastOrNull()
    }

    fun addToPolishNotationStack(input: String): String? {
        if (input.isDigitsOnly()) {
            stack.addLast(input.toFloat())
            return null
        } else {
            val operations = input.split(" ").toTypedArray()
            operations.forEach { char ->
                Log.d("nathanx", "char $char")
                val current = char.toFloatOrNull()
                if (current != null) {
                    stack.addLast(current)
                    Log.d("nathanxxx", "add last ${stack.toString()}")
                } else {
                    if (stack.size < 1) {
                        throw InvalidMathOperation()
                    }
                    Log.d("nathanxxx", "else ${stack.toString()}")
                    val second = stack.removeLast()
                    Log.d("nathanxxx", "second ${stack.toString()}")
                    val first = stack.removeLast()
                    Log.d("nathanxxx", "first ${stack.toString()}")
                    val result = calculate(first, second, char)
                    stack.addLast(result)
                    Log.d("nathanxxx", "add last else ${stack.toString()}")
                }
            }
            Log.d("nathanx", "stack size  ${stack.size} ")
            return stack.last().toString()
        }
    }

//    fun calculateInput(list: List<String>): Int {
//        //val strs = "5 5 5 8 + + -".split(" ").toTypedArray()
//        val stack = ArrayDeque<Int>()
//
//        list.forEach { char ->
//            Log.d("nathanx", "char $char")
//            val current = char.toIntOrNull()
//            if (current != null) {
//                stack.addLast(current)
//                Log.d("nathanxxx", "add last ${stack.toString()}")
//            } else {
//                Log.d("nathanxxx", "else ${stack.toString()}")
//                val second = stack.removeLast()
//                Log.d("nathanxxx", "second ${stack.toString()}")
//                val first = stack.removeLast()
//                Log.d("nathanxxx", "first ${stack.toString()}")
//                val result = calculate(first, second, char)
//                stack.addLast(result)
//                Log.d("nathanxxx", "add last else ${stack.toString()}")
//            }
//        }
//        Log.d("nathanx", "stack size  ${stack.size} ")
//        return stack.last()
//    }
//
//    fun updateStack(char: String): Int {
//        Log.d("nathanx", "char $char")
//        val current = char.toIntOrNull()
//        if (current != null) {
//            stack.addLast(current)
//            Log.d("nathanxxx", "add last ${stack.toString()}")
//        } else {
//            Log.d("nathanxxx", "else ${stack.toString()}")
//            val second = stack.removeLast()
//            Log.d("nathanxxx", "second ${stack.toString()}")
//            val first = stack.removeLast()
//            Log.d("nathanxxx", "first ${stack.toString()}")
//            val result = calculate(first, second, char)
//            stack.addLast(result)
//            Log.d("nathanxxx", "add last else ${stack.toString()}")
//        }
//        return stack.last()
//    }

    private fun calculate(first: Float, second: Float, operator: String): Float {
        val test = when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> 0F
        }
        Log.d("nathanxx", "$first $second $operator $test")
        return test
    }

    fun isValidInput(inputExpression: String): Boolean {
        if (Pattern.compile("[A-Za-z]").matcher(inputExpression).find()) {
            throw InvalidInput()
        } else if (!Pattern.compile("[-+*/()0-9]").matcher(inputExpression).find()) {
            throw InvalidInput()
        }
        return true;
    }


    class InvalidInput :
        Exception("Invalid Character. Character must be a number or math operator (+, -, *, /)")

    class InvalidMathOperation :
        Exception("Not a valid math operation")
}