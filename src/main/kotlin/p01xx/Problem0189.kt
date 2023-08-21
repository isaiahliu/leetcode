package p01xx

import util.expect

fun main() {
    class Solution {
        fun rotate(nums: IntArray, k: Int): Unit {
            val numSize = nums.size

            val k2 = k % numSize
            if (k2 == 0) {
                return
            }

            if (numSize <= 1) {
                return
            }

            val gcd = numSize.toBigInteger().gcd(k2.toBigInteger()).toInt()

            repeat(gcd) { step ->
                val tNum = nums[step]

                var t = step
                repeat(numSize / gcd - 1) {
                    val nextT = (t - k2 + numSize) % numSize
                    nums[t] = nums[nextT]

                    t = nextT
                }

                nums[t] = tNum
            }
        }
    }

    expect {
        Solution().rotate(
            intArrayOf(1, 2), 3
        )
    }
}

