package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimalKSum(nums: IntArray, k: Int): Long {
            var result = 0L

            nums.sort()
            var next = 1
            var processed = 0

            loop@ for (num in nums) {
                while (processed < k && next < num) {
                    processed++
                    result += next++
                }

                if (processed == k) {
                    break
                }

                next = num + 1
            }

            while (processed < k) {
                processed++
                result += next++
            }

            return result
        }
    }

    expect {
        Solution().minimalKSum(
            intArrayOf(1, 4, 25, 10, 25), 2
        )
    }
}