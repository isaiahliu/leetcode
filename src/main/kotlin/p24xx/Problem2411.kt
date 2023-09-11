package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun smallestSubarrays(nums: IntArray): IntArray {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val map = hashMapOf<Int, TreeSet<Int>>()

            nums.forEachIndexed { index, num ->
                num.forEachBit {
                    map.computeIfAbsent(it) { TreeSet() }.add(index)
                }
            }

            return nums.indices.map { index ->
                map.values.mapNotNull { it.ceiling(index) }.maxOrNull()?.let { it - index + 1 } ?: 1
            }.toIntArray()
        }
    }

    expect {
        Solution().smallestSubarrays(
            intArrayOf()
        )
    }
}