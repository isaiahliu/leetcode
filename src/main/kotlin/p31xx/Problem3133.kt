package p31xx

import util.expect

fun main() {
    class Solution {
        fun minEnd(n: Int, x: Int): Long {
            var bNum1 = x
            var bNum2 = n - 1

            var result = 0L
            var base = 1L

            while (bNum1 > 0) {
                result += base * ((bNum1 % 2).takeIf { it == 1 } ?: run {
                    (bNum2 % 2).also { bNum2 /= 2 }
                })

                bNum1 /= 2
                base *= 2
            }

            while (bNum2 > 0) {
                result += base * (bNum2 % 2)
                bNum2 /= 2
                base *= 2
            }

            return result
        }
    }

    expect {
        Solution().minEnd(
            3, 4
        )
    }
}
