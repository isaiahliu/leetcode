package p01xx

fun main() {
    class Solution {
        fun largestNumber(nums: IntArray): String {
            return nums.map { it.toString() }.sortedWith { o1: String, o2: String ->
                (o2 + o1).compareTo(o1 + o2)
            }.joinToString("").trimStart('0').ifEmpty { "0" }
        }
    }

    println(
        Solution().largestNumber(
            intArrayOf(3, 30, 34, 5, 9)
        )
    )
}

