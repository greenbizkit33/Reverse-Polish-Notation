package com.nathanhaze.snapcalculator.util

import androidx.core.text.isDigitsOnly
import java.util.regex.Pattern

object PolishNotationUtil {

    private val stack = ArrayDeque<Float>()

    fun clearStack() {
        stack.clear()
    }

    fun getStack(): ArrayDeque<Float> {
        return stack
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
                val current = char.toFloatOrNull()
                if (current != null) {
                    stack.addLast(current)
                } else {
                    if (stack.size < 2) {
                        throw InvalidStack()
                    }
                    val second = stack.removeLast()
                    val first = stack.removeLast()
                    val result = calculate(first, second, char)
                    result?.let { output ->
                        stack.addLast(output)
                    } ?: throw InvalidMathOperation()
                }
            }
            return stack.last().toString()
        }
    }

    private fun calculate(first: Float, second: Float, operator: String): Float? {
        return when (operator) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> first / second
            else -> null
        }
    }

    fun isValidInput(inputExpression: String): Boolean {
        if (Pattern.compile("[A-Za-z]").matcher(inputExpression).find()) {
            throw InvalidInput()
        } else if (!Pattern.compile("[-+*/0-9]").matcher(inputExpression).find()) {
            throw InvalidInput()
        }
        return true
    }

    fun calculateInput(list: List<String>): Float {
        val stack = ArrayDeque<Float>()

        list.forEach { char ->
            val current = char.toFloatOrNull()
            if (current != null) {
                stack.addLast(current)
            } else {
                val second = stack.removeLast()
                val first = stack.removeLast()
                val result = calculate(first, second, char)
                result?.let { output ->
                    PolishNotationUtil.stack.addLast(output)
                } ?: throw InvalidMathOperation()
            }
        }
        return stack.last()
    }

    class InvalidInput :
        Exception("Invalid Character. Character must be a number or math operator (+, -, *, /)")

    class InvalidMathOperation :
        Exception("Not a valid math operation")

    class InvalidStack :
        Exception("Your stack is not large enough for that operation")
}