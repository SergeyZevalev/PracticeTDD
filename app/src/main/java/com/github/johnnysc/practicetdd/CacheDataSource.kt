package com.github.johnnysc.practicetdd

interface CacheDataSource {

    fun add(item: SimpleData)
    fun data() : List<SimpleData>

    class Timed(private val now: Now, private val lifeTimeMillis: Long) : CacheDataSource {

        var data: MutableList<SimpleData> = mutableListOf()
        override fun add(item: SimpleData) {
            data.add(item)
        }

        override fun data(): List<SimpleData> {
            if (now.now() > lifeTimeMillis) {
                data.removeAt(0)
            }
            return data
        }

    }
}