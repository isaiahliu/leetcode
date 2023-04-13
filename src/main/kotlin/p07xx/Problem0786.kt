package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
            val set = TreeSet<Pair<Int, Int>>(compareBy { (first, second) -> arr[first].toDouble() / arr[second] })

            set.add(0 to arr.lastIndex)

            var result: Pair<Int, Int> = 0 to 0

            repeat(k) {
                result = set.first()

                arrayOf(result.first + 1 to result.second, result.first to result.second - 1).filter { (f, s) ->
                    f in arr.indices && s in arr.indices && set.lower(f to s) != null
                }.forEach {
                    set.add(it)
                }

                set.pollFirst()
            }

            return result.let { intArrayOf(arr[it.first], arr[it.second]) }
        }
    }

    measureTimeMillis {
        Solution().kthSmallestPrimeFraction(
            intArrayOf(1, 2, 3, 5),
            3,
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}