package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rotateString(s: String, goal: String): Boolean {
            return s.length == goal.length && s in "${goal}${goal}"
        }
    }

    measureTimeMillis {
        Solution().rotateString(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}