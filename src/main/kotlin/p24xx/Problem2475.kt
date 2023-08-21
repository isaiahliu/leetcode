package p24xx

import util.expect

fun main() {
    class Solution {
        fun unequalTriplets(nums: IntArray): Int {
            return nums.toList().groupingBy { it }.eachCount().values.fold(0 to 0) { (visited, result), current ->
                (visited + current).let {
                    it to (result + current * visited * (nums.size - it))
                }
            }.second
        }
    }

    expect {
        Solution().unequalTriplets(
            intArrayOf(1, 4, 4, 5, 5)
        )
    }
}
