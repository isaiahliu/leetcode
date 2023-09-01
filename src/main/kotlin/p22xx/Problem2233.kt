package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumProduct(nums: IntArray, k: Int): Int {
            if (nums.size == 1) {
                return nums[0] + k
            }

            nums.sort()

            var remain = k
            var current = nums[0]
            var size = 1

            var result = 1L
            val m = 1000000007
            val mi = m.toBigInteger()

            for (index in 1 until nums.size) {
                val num = nums[index]

                when {
                    num * size - current * size <= remain -> {
                        remain -= num * size - current * size
                        size++
                        current = num
                    }

                    else -> {
                        result *= num
                        result %= m
                    }
                }
            }

            (remain / size).also {
                remain -= it * size
                current += it
            }

            current.toBigInteger().modPow((size - remain).toBigInteger(), mi).also {
                result *= it.toLong()
                result %= m
            }

            (current + 1).toBigInteger().modPow(remain.toBigInteger(), mi).also {
                result *= it.toLong()
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().maximumProduct(
            intArrayOf(24, 5, 64, 53, 26, 38), 54
        )
    }
}