package p24xx

import util.expect

fun main() {
    class Solution {
        fun mostFrequentEven(nums: IntArray): Int {
            return nums.filter { it % 2 == 0 }.groupingBy { it }.eachCount()
                .minWithOrNull(compareByDescending<Map.Entry<Int, Int>> { it.value }.thenBy { it.key })?.key ?: -1
        }
    }

    expect {
        Solution().mostFrequentEven(
            intArrayOf()
        )
    }
}