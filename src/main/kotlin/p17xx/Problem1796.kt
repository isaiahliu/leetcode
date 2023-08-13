package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun secondHighest(s: String): Int {
            val treeSet = TreeSet<Int>()

            s.forEach {
                if (it in '0'..'9') {
                    treeSet.add(it - '0')
                }
            }

            treeSet.pollLast()

            return treeSet.pollLast() ?: -1
        }
    }

    measureTimeMillis {
        Solution().secondHighest(
            ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
