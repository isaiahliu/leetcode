package p21xx

import util.expect

fun main() {
    class Solution {
        fun numberOfWays(corridor: String): Int {
            val counts = hashMapOf<Int, Int>()

            var sum = 0
            corridor.forEach {
                if (it == 'S') {
                    sum++
                }

                if (sum % 2 == 0) {
                    counts[sum] = (counts[sum] ?: 0) + 1
                }
            }

            return if (sum % 2 == 1 || sum == 0) {
                0
            } else {
                counts.remove(sum)
                counts.remove(0)

                counts.values.fold(1L) { a, b ->
                    a * b % 1000000007
                }.toInt()
            }
        }
    }

    expect {
        Solution().numberOfWays(
            "SSPPSPS"
        )
    }
}