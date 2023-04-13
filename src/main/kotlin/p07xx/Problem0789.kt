package p07xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun escapeGhosts(ghosts: Array<IntArray>, target: IntArray): Boolean {
            return ghosts.all { (x, y) ->
                (x - target[0]).absoluteValue + (y - target[1]).absoluteValue > target[0].absoluteValue + target[1].absoluteValue
            }
        }
    }

    measureTimeMillis {
        Solution().escapeGhosts(
            arrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}