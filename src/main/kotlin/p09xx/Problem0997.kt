package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findJudge(n: Int, trust: Array<IntArray>): Int {
            val trusted = IntArray(n + 1)

            val judges = hashSetOf<Int>()
            repeat(n) {
                judges.add(it + 1)
            }

            trust.forEach { (from, to) ->
                trusted[to]++
                judges.remove(from)
            }

            judges.retainAll {
                trusted[it] == n - 1
            }

            return judges.takeIf { it.size == 1 }?.firstOrNull() ?: -1
        }
    }

    measureTimeMillis {
        Solution().findJudge(
            1, arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
