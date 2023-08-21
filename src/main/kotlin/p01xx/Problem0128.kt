package p01xx

import util.expect

fun main() {
    class Solution {
        fun longestConsecutive(nums: IntArray): Int {
            val set = nums.toMutableSet()

            var result = 0
            while (set.size > result) {
                val num = set.first()

                var length = 1

                set.remove(num)

                var t = num - 1
                while (t in set) {
                    set.remove(t--)
                    length++
                }

                t = num + 1
                while (t in set) {
                    set.remove(t++)
                    length++
                }

                result = result.coerceAtLeast(length)
            }

            return result
        }
    }

    expect {
        Solution().longestConsecutive(
            intArrayOf()
        )
    }
}

