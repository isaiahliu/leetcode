package p23xx

fun main() {
    class Solution {
        fun numberOfPairs(nums: IntArray): IntArray {
            val counts = IntArray(101)

            nums.forEach { counts[it]++ }

            return intArrayOf(counts.sumOf { it / 2 }, counts.sumOf { it % 2 })
        }
    }

}

