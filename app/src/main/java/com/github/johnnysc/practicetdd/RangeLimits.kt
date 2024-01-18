package com.github.johnnysc.practicetdd

interface RangeLimits {

    fun pair(number: Int) : RangePair

    class Base(private val list: List<Int>): RangeLimits {

        override fun pair(number: Int): RangePair {
            if (list == emptyList<Int>()) return RangePair(Int.MIN_VALUE, Int.MAX_VALUE)
            var left: Int? = null
            var right: Int? = null
            for (i in list) {
                if (i < number) left = i
                if (i > number){
                    right = i
                    break
                }
            }
            if (left == null) left = Int.MIN_VALUE
            if (right == null) right = Int.MAX_VALUE
            return RangePair(left, right)
        }

    }
}

data class RangePair(private val left: Int, private val right: Int)