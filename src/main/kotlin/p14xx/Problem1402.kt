package p14xx

fun main() {
    class Solution {
        fun maxSatisfaction(satisfaction: IntArray): Int {
            val results = satisfaction.filter { it >= 0 }.sorted().toMutableList()
            val negatives = satisfaction.filter { it < 0 }.sortedDescending()

            var result = results.mapIndexed { index, i -> i * (index + 1) }.sum()
            val posAdd = results.sum()
            var negAdd = 0
            for (neg in negatives) {
                if (posAdd + negAdd + neg >= 0) {
                    negAdd += neg
                    result += posAdd + negAdd
                } else {
                    break
                }
            }

            return result
        }
    }
    println(Solution().maxSatisfaction(intArrayOf(-1, -8, 0, 5, -7)))

}

