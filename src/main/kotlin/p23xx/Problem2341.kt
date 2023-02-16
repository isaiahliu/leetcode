package p23xx

fun main() {
    class Solution {
        fun numberOfPairs(nums: IntArray): IntArray {
            val counts = IntArray(101)

            var pairCount = 0
            var singleCount = 0

            nums.forEach {
                when (counts[it]) {
                    0 -> {
                        counts[it]++
                        singleCount++
                    }

                    1 -> {
                        counts[it]--
                        pairCount++
                        singleCount--
                    }
                }
            }

            return intArrayOf(pairCount, singleCount)
        }
    }

}

