package com.github.johnnysc.practicetdd

import java.lang.IllegalStateException

interface Validation {

    fun isValid(text: String): Result

    class Password(
        private val minLength: Int = 1,
        private val upperCaseLettersCount: Int = 0,
        private val lowerCaseLettersCount: Int = 0,
        private val numbersCount: Int = 0,
        private val specialSignsCount: Int = 0,

        ) : Validation {

        private var upperCaseLettersCountInput = 0
        private var lowerCaseLettersCountInput = 0
        private var numbersCountInput = 0
        private var specialSignsCountInput = 0

        init {
            if (minLength <= 0 || upperCaseLettersCount < 0 || lowerCaseLettersCount < 0 || numbersCount < 0 || specialSignsCount < 0) {
                throw IllegalStateException()
            }
        }

        override fun isValid(text: String): Result {
            if (text.length < minLength) return Result.MinLengthInsufficient(minLength)
            for (i in text) {
                if (i.isUpperCase()) upperCaseLettersCountInput++
                if (i.isLowerCase()) lowerCaseLettersCountInput++
                if (i.isDigit()) numbersCountInput++
                if (!i.isLetterOrDigit()) specialSignsCountInput++
            }

            if (upperCaseLettersCountInput < upperCaseLettersCount) return Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount)
            if (lowerCaseLettersCountInput < lowerCaseLettersCount) return Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount)
            if (numbersCountInput < numbersCount) return Result.NumbersCountInsufficient(numbersCount)
            if (specialSignsCountInput < specialSignsCount) return Result.SpecialSignsInsufficient(specialSignsCount)

            return Result.Valid
        }

    }

    abstract class Result() {


        object Valid : Result()
        data class MinLengthInsufficient(private val minLength: Int) : Result()
        data class UpperCaseLettersCountInsufficient(private val upperCaseLettersCount: Int) : Result()
        data class LowerCaseLettersCountInsufficient(private val lowerCaseLettersCount: Int) : Result()
        data class NumbersCountInsufficient(private val numbersCount: Int) : Result()
        data class SpecialSignsInsufficient(private val specialSignsCount: Int) : Result()
    }
}