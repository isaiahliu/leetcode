package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun avoidFlood(rains: IntArray): IntArray {
            val full = hashMapOf<Int, Int>()
            val sunnyDays = TreeSet<Int>()

            val result = IntArray(rains.size) { -1 }

            rains.forEachIndexed { index, i ->
                if (i > 0) {
                    full[i]?.also {
                        sunnyDays.higher(it)?.also {
                            sunnyDays.remove(it)
                            result[it] = i
                        } ?: return intArrayOf()
                    }
                    full[i] = index
                } else {
                    sunnyDays.add(index)
                }
            }
            
            //????
            sunnyDays.forEach {
                result[it] = 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().avoidFlood(
            intArrayOf(1, 2, 3, 4)
        ).toList().also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

