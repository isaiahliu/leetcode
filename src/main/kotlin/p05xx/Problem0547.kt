package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCircleNum(isConnected: Array<IntArray>): Int {
            var provinceCount = 0

            val map = isConnected.indices.associateWith { mutableSetOf<Int>() }.toMutableMap()

            isConnected.forEachIndexed { l, row ->
                for (i in l + 1 until row.size) {
                    if (row[i] == 1) {
                        map[l]?.add(i)
                        map[i]?.add(l)
                    }
                }
            }

            while (map.isNotEmpty()) {
                provinceCount++

                val cities = hashSetOf(map.keys.min())

                while (cities.isNotEmpty()) {
                    val city = cities.first()
                    cities.remove(city)

                    if (city in map) {
                        cities.addAll(map[city].orEmpty())
                        map.remove(city)
                    }
                }
            }

            return provinceCount
        }
    }

    measureTimeMillis {
        Solution().findCircleNum(
            arrayOf(
                intArrayOf(1, 0, 0, 1),
                intArrayOf(0, 1, 1, 0),
                intArrayOf(0, 1, 1, 1),
                intArrayOf(1, 0, 1, 1)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}