package p15xx

import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun closestToTarget(arr: IntArray, target: Int): Int {
            var result = Int.MAX_VALUE

            var set = mutableSetOf<Int>()

            arr.forEach { num ->
                set = set.map {
                    (it and num).also {
                        result = result.coerceAtMost((it - target).absoluteValue)
                    }
                }.toMutableSet()
                set.add(num)
                result = result.coerceAtMost((num - target).absoluteValue)
            }

            return result
        }
    }

    expect {
        Solution().closestToTarget(
            intArrayOf(9, 12, 3, 7, 15), 5
        )
    }
}

