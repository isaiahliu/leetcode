package p04xx

import util.expect

fun main() {
    class Solution {
        fun thirdMax(nums: IntArray): Int {
            var b1: Int? = null
            var b2: Int? = null
            var b3: Int? = null

            nums.forEach {
                when {
                    b1 == null -> b1 = it
                    b1!! < it -> {
                        b3 = b2
                        b2 = b1
                        b1 = it
                    }

                    b1 == it -> {}
                    b2 == null -> {
                        b2 = it
                    }

                    b2!! < it -> {
                        b3 = b2
                        b2 = it
                    }

                    b2 == it -> {}

                    b3 == null -> b3 = it
                    b3!! < it -> {
                        b3 = it
                    }
                }
            }

            return b3 ?: b1 ?: 0
        }
    }

    expect {
        Solution().thirdMax(
            intArrayOf(1, 2, 3, 4)
        )
    }
}


