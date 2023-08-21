package p00xx

fun main() {
    class Solution {
        fun maxArea(height: IntArray): Int {
            var max = 0
            for (i in height.indices) {
                val num = height[i]

                if (num == 0) {
                    continue
                }

                for (j in i + 1 + max / num until height.size) {
                    ((j - i) * height[i].coerceAtMost(height[j])).also {
                        max = max.coerceAtLeast(it)
                    }
                }
            }

            return max
        }
    }

    Solution().maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7))
}

