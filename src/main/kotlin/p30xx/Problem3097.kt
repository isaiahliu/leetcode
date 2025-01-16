package p30xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
            var left = 0
            var right = 0

            val kBits = IntArray(32) {
                ((1L shl it) and k.toLong()).sign
            }

            val bits = IntArray(32)

            fun match(): Boolean {
                for (i in bits.lastIndex downTo 0) {
                    when {
                        bits[i].sign > kBits[i] -> {
                            return true
                        }

                        bits[i] < kBits[i] -> {
                            return false
                        }
                    }
                }

                return true
            }

            var result = Int.MAX_VALUE
            loop@ while (result > 1 && right < nums.size) {
                while (true) {
                    var t = nums.getOrNull(right++) ?: break@loop
                    var i = 0
                    while (t > 0) {
                        if (t % 2 == 1) {
                            bits[i]++
                        }

                        t /= 2
                        i++
                    }

                    if (match()) {
                        break
                    }
                }

                while (match() && left < right) {
                    result = minOf(result, right - left)

                    var t = nums[left++]
                    var i = 0

                    while (t > 0) {
                        if (t % 2 == 1) {
                            bits[i]--
                        }

                        t /= 2
                        i++
                    }
                }
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    expect {
        Solution().minimumSubarrayLength(
            intArrayOf(1, 2), 0
        )
    }
}
