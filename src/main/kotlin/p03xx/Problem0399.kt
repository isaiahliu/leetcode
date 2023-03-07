package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
            val map = hashMapOf<String, MutableMap<String, Double>>()

            equations.forEachIndexed { index, (from, to) ->
                map.computeIfAbsent(from) { hashMapOf() }[to] = values[index]
                map.computeIfAbsent(to) { hashMapOf() }[from] = 1.0 / values[index]
            }

            fun evaluate(from: String, to: String, current: Double = 1.0, route: Set<String> = setOf(from)): Double? {
                return map[from]?.let {
                    if (from == to) {
                        return 1.0
                    }
                    if (to in it) {
                        current * (it[to] ?: 1.0)
                    } else {
                        var result: Double? = null

                        for ((key, value) in it) {
                            if (key !in route) {
                                result = evaluate(key, to, current * value, route + key)
                            }

                            if (result != null) {
                                break
                            }
                        }

                        result
                    }
                }
            }

            return queries.map { (from, to) -> evaluate(from, to) ?: -1.0 }.toDoubleArray()
        }
    }

    measureTimeMillis {
        Solution().calcEquation(
            listOf(listOf("a", "b"), listOf("b", "c")), doubleArrayOf(2.0, 3.0), listOf(listOf("a", "c"))
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


