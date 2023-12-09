package com.github.johnnysc.practicetdd

import java.lang.IllegalStateException

interface MyStack<L> {
    fun pop(): L

    fun push(item: L)

    class FIFO<L>(maxCount: Int) : Base<L>(maxCount) {
        override val objectList: ArrayList<L> = ArrayList(maxCount)

        override fun pop(): L {
            try {
                val item = objectList[0]
                objectList.remove(item)
                return item
            } catch (e: Exception) {
                throw IllegalStateException()
            }
        }
    }

    class LIFO<L>(maxCount: Int) : Base<L>(maxCount) {
        override val objectList: ArrayList<L> = ArrayList(maxCount)
        override fun pop(): L {
            try {
                val item = objectList[objectList.size - 1]
                objectList.remove(item)
                return item
            } catch (e: Exception) {
                throw IllegalStateException()
            }
        }
    }

}

abstract class Base<L>(maxCount: Int): MyStack<L>{

    abstract val objectList: ArrayList<L>

    init {
        if (maxCount <= 0) throw IllegalStateException()
    }

    override fun push(item: L) {
        try {
            objectList.add(item)
        } catch (e: Exception) {
            throw IllegalStateException("Stack overflow exception, maximum is ${objectList.size}")
        }
    }
}

