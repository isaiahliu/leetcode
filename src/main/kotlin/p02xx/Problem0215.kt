package p02xx

fun main() {
    class Solution {
        fun findKthLargest(nums: IntArray, k: Int): Int {
            val counts = IntArray(20001)

            var maxIndex = 0
            var minIndex = counts.size

            nums.forEach {
                (it + 10000).also {
                    counts[it]++
                    maxIndex = maxIndex.coerceAtLeast(it)
                    minIndex = minIndex.coerceAtMost(it)
                }
            }

            var foundIndex = 0
            var walkedCount = 0
            if (k > nums.size / 2) {
                foundIndex = maxIndex
                walkedCount = counts[foundIndex]

                while (walkedCount < k) {
                    walkedCount += counts[--foundIndex]
                }
            } else {
                foundIndex = minIndex
                walkedCount = counts[foundIndex]

                while (walkedCount < nums.size - k + 1) {
                    walkedCount += counts[++foundIndex]
                }
            }

            return foundIndex - 10000
        }
    }

    println(
        Solution().findKthLargest(
            intArrayOf(3, 2, 1, 5, 6, 4), 2
        )
    )
}

