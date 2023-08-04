package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Fancy {
        val list = arrayListOf<IntArray>()

        var actions = arrayListOf<Pair<Int, Int>>()

        fun append(`val`: Int) {
            list.add(intArrayOf(`val`, actions.size - 1))
        }

        fun addAll(inc: Int) {
            actions.add(0 to inc)
        }

        fun multAll(m: Int) {
            actions.add(1 to m)
        }

        val m = 1000000007
        fun getIndex(idx: Int): Int {
            return list.getOrNull(idx)?.let {
                var result = it[0].toLong()

                for (actionIndex in it[1] + 1 until actions.size) {
                    val (action, param) = actions[actionIndex]

                    when (action) {
                        0 -> {
                            result += param
                        }

                        1 -> {
                            result *= param
                        }
                    }

                    result %= m
                }

                it[0] = result.toInt()
                it[1] = actions.size - 1

                result.toInt()
            } ?: -1
        }
    }

    measureTimeMillis {
        Fancy().append(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}