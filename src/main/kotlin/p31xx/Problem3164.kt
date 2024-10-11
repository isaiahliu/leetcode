package p31xx

import util.expect
import java.util.*
import kotlin.math.sign

fun main() {
    class Solution {
        fun numberOfPairs(nums1: IntArray, nums2: IntArray, k: Int): Long {
            val map1 = TreeMap<Int, Int>()

            nums1.forEach {
                if (it % k == 0) {
                    map1[it / k] = (map1[it / k] ?: 0) + 1
                }
            }

            return nums2.sumOf { num2 ->
                var result = 0L
                var t = num2

                while (true) {
                    map1[t]?.also {
                        result += it
                    }

                    map1.higherKey(t)?.also {
                        t = (it / num2 + (it % num2).sign) * num2
                    } ?: break
                }

                result
            }
        }
    }

    expect {
        Solution().numberOfPairs(
            intArrayOf(0, 1, 1, 1), intArrayOf(), 1
        )
    }
}
