package p21xx

import util.expect

fun main() {
    class Solution {
        fun mostFrequent(nums: IntArray, key: Int): Int {
            return (0 until nums.lastIndex).filter { nums[it] == key }.groupingBy { nums[it + 1] }.eachCount()
                .maxBy { it.value }.key
        }
    }

    expect {
        Solution().mostFrequent(
            intArrayOf(), 1
        )
    }
}