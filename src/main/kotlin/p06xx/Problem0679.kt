package p06xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun judgePoint24(cards: IntArray): Boolean {
            val dp1 = Array(4) {
                setOf(cards[it].toDouble())
            }
            val dp2 = hashMapOf<Pair<Int, Int>, Set<Double>>()
            val dp3 = hashMapOf<Pair<Pair<Int, Int>, Int>, Set<Double>>()

            fun join(set1: Set<Double>, set2: Set<Double>): Set<Double> {
                val res = hashSetOf<Double>()

                set1.forEach { s1 ->
                    set2.forEach { s2 ->
                        res.add(s1 + s2)
                        res.add(s1 - s2)
                        res.add(s2 - s1)
                        res.add(s1 * s2)
                        res.add(s1 / s2)
                        res.add(s2 / s1)
                    }
                }

                return res
            }

            dp2[0 to 1] = join(dp1[0], dp1[1])
            dp2[0 to 2] = join(dp1[0], dp1[2])
            dp2[0 to 3] = join(dp1[0], dp1[3])
            dp2[1 to 2] = join(dp1[1], dp1[2])
            dp2[1 to 3] = join(dp1[1], dp1[3])
            dp2[2 to 3] = join(dp1[2], dp1[3])

            fun Pair<Pair<Int, Int>, Int>.process(): Set<Double> {
                val (ab, c) = this
                val (a, b) = ab

                val result = hashSetOf<Double>()

                result.addAll(join(dp1[a], dp2[b to c].orEmpty()))
                result.addAll(join(dp1[b], dp2[a to c].orEmpty()))
                result.addAll(join(dp1[c], dp2[a to b].orEmpty()))

                return result
            }

            arrayOf(
                0 to 1 to 2,
                0 to 1 to 3,
                0 to 2 to 3,
                1 to 2 to 3
            ).forEach {
                dp3[it] = it.process()
            }

            val dp4 = hashSetOf<Double>()

            dp4.addAll(join(dp1[0], dp3[1 to 2 to 3].orEmpty()))
            dp4.addAll(join(dp1[1], dp3[0 to 2 to 3].orEmpty()))
            dp4.addAll(join(dp1[2], dp3[0 to 1 to 3].orEmpty()))
            dp4.addAll(join(dp1[3], dp3[0 to 1 to 2].orEmpty()))
            dp4.addAll(join(dp2[0 to 1].orEmpty(), dp2[2 to 3].orEmpty()))
            dp4.addAll(join(dp2[0 to 2].orEmpty(), dp2[1 to 3].orEmpty()))
            dp4.addAll(join(dp2[0 to 3].orEmpty(), dp2[1 to 2].orEmpty()))

            return dp4.any {
                (it - 24.0).absoluteValue < 0.000001
            }
        }
    }

    measureTimeMillis {
        Solution().judgePoint24(intArrayOf(1, 2, 1, 2)).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}