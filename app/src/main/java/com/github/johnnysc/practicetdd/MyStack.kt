package com.github.johnnysc.practicetdd

import java.lang.IllegalStateException

interface MyStack<L> {
    fun pop(): L

    fun push(item: L)

    class FIFO<L>(maxCount: Int) : Base<L>(maxCount) {

        override fun pop(): L {
            return super.popFIFO()
        }
    }

    class LIFO<L>(maxCount: Int) : Base<L>(maxCount) {
        override fun pop(): L {
            return super.popLIFO()
        }
    }

}

abstract class Base<L>(maxCount: Int): MyStack<L>{

    private val objectList: ArrayList<L>

    init {
        if (maxCount <= 0) throw IllegalStateException()
        objectList = ArrayList(maxCount)
    }

    fun popFIFO(): L {
        try {
            val item = objectList[0]
            objectList.remove(item)
            return item
        } catch (e: Exception) {
            throw IllegalStateException()
        }
    }

    fun popLIFO(): L {
        try {
            val item = objectList[objectList.size - 1]
            objectList.remove(item)
            return item
        } catch (e: Exception) {
            throw IllegalStateException()
        }
    }

    override fun push(item: L) {
        try {
            objectList.add(item)
        } catch (e: Exception) {
            throw IllegalStateException("Stack overflow exception, maximum is ${objectList.size}")
        }
    }
}

