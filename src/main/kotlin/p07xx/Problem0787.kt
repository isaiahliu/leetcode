package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
            val flightMap = flights.groupBy { it[0] }

            var result: Int? = null

            val map = hashMapOf<Int, TreeMap<Int, Int>>()

            fun find(from: Int, turn: Int, total: Int = 0) {
                when {
                    total >= (result ?: Int.MAX_VALUE) -> {
                        return
                    }

                    turn > k -> {
                        return
                    }

                    else -> flightMap[from]?.forEach { (_, to, price) ->
                        val newPrice = total + price

                        if (to == dst) {
                            result = newPrice.coerceAtMost(result ?: Int.MAX_VALUE)
                            return@forEach
                        } else {
                            val cache = map.computeIfAbsent(to) { TreeMap() }

                            if (cache.lowerEntry(turn + 1)?.value?.takeIf { it <= newPrice } != null) {
                                return@forEach
                            }

                            cache[turn] = newPrice

                            find(to, turn + 1, newPrice)
                        }
                    }
                }
            }

            find(src, 0)

            return result ?: -1
        }
    }

    measureTimeMillis {
        Solution().findCheapestPrice(
            5,
            arrayOf(
                intArrayOf(0, 1, 5),
                intArrayOf(1, 2, 5),
                intArrayOf(0, 3, 2),
            ),

            4,
            3,
            7
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}