package com.github.johnnysc.practicetdd

import java.lang.Exception
import java.lang.IllegalStateException
import java.util.regex.Pattern

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(private val delimiter: String) : Parser {

        private val byteMatchPattern = "^-?[1-2][0-9]{0,2}$".toRegex()
        private val shortMatchPattern = "^-?[1-9]{3,5}$".toRegex()
        private val intMatchPattern = "^-?[1-9]{5,10}$".toRegex()
        private val longMatchPattern = "^-?[1-9]{10,19}".toRegex()
        private val floatMatchPattern = "^-?[0-9]+.?[0-9]f?$".toRegex()
        private val doubleMatchPattern = "^-?[0-9]+.?[0-9]{1,2}$".toRegex()

        private val output: MutableList<Any> = mutableListOf()
        private var aObject: String = ""

        init {
            if (delimiter == "") throw IllegalStateException()
        }

        override fun parse(raw: String): List<Any> {

            if (raw == "") return emptyList()
            if (raw == delimiter) return emptyList()

            for (i in raw) {
                if (i.toString() != delimiter) aObject += i
                else {
                    parseInput(aObject).let { output.add(it) }
                    aObject = ""
                }
            }

            parseInput(aObject).let { output.add(it) }

            return output

        }

        private fun parseInput(input: String) : Any {

            if (input.length == 1) return input[0]

            if (input.matches("^-?\\d*.?[0-9]{1,2}?f?$".toRegex())) {

                if (input.matches(byteMatchPattern) && (input.toShort() in Byte.MIN_VALUE ..  Byte.MAX_VALUE)) return input.toByte()
                if (input.matches(shortMatchPattern) && (input.toInt() in Short.MIN_VALUE .. Short.MAX_VALUE)) return input.toShort()
                if (input.matches(intMatchPattern) && (input.toLong() in Int.MIN_VALUE ..Int.MAX_VALUE)) return input.toInt()
                if (input.matches(longMatchPattern) && (input.toFloat() >= Long.MIN_VALUE || input.toFloat() <= Long.MAX_VALUE)) return input.toLong()
                if (input.matches(floatMatchPattern) && (input.toDouble() in Float.MIN_VALUE ..Float.MAX_VALUE)) return input.toFloat()
                if (input.matches(doubleMatchPattern)) return input.toDouble()
            }

            return when (input.lowercase()) {
                "true" -> true
                "false" -> false
                else -> {
                    input
                }
            }
        }
    }
}