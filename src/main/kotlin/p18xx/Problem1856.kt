package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxSumMinProduct(nums: IntArray): Int {
            val lengths = Array(nums.size) {
                IntArray(2)
            }
            arrayOf(
                { index: Int -> index },
                { index: Int -> nums.lastIndex - index }).forEachIndexed { lengthIndex, indexGetter ->
                val map = LinkedList<Pair<Int, Int>>()

                for (index in nums.indices) {
                    val numIndex = indexGetter(index)
                    val num = nums[numIndex]

                    var size = 1
                    while (map.isNotEmpty() && map.peekLast().first >= num) {
                        size += map.pollLast().second
                    }

                    map.add(num to size)
                    lengths[numIndex][lengthIndex] = size
                }
            }
            var result = 0L

            var sum = 0L
            val sums = LongArray(nums.size) {
                sum += nums[it]
                sum
            }
            lengths.forEachIndexed { index, l ->
                result = result.coerceAtLeast(
                    nums[index].toLong() * (sums[index + l[1] - 1] - (sums.getOrNull(index - l[0]) ?: 0))
                )
            }

            return (result % 1000000007).toInt()
        }
    }

    expect {
        Solution().maxSumMinProduct(
            intArrayOf(3, 1, 5, 6, 4, 2)
        )

    }
}
