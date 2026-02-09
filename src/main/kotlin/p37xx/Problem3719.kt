package p37xx

import util.expect

fun main() {
    class Solution {
        fun longestBalanced(nums: IntArray): Int {
            fun MutableMap<Int, Int>.push(num: Int) {
                this[num] = (this[num] ?: 0) + 1
            }

            fun MutableMap<Int, Int>.pop(num: Int) {
                this[num]?.also {
                    if (it == 1) {
                        this.remove(num)
                    } else {
                        this[num] = it - 1
                    }
                }
            }

            var result = nums.size

            while (result >= 0) {
                val counts = arrayOf(hashMapOf<Int, Int>(), hashMapOf())

                repeat(result) {
                    counts[nums[it] % 2].push(nums[it])
                }

                var offset = 0
                while (counts[0].size != counts[1].size && result + offset < nums.size) {
                    counts[nums[offset] % 2].pop(nums[offset])
                    counts[nums[result + offset] % 2].push(nums[result + offset])

                    offset++
                }

                if (counts[0].size == counts[1].size) {
                    break
                }

                result--
            }

            return result
        }
    }

    expect {
        Solution().longestBalanced(
            intArrayOf(2, 5, 4, 3)
        )
    }
}
