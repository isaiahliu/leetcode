package p10xx

import util.expect

fun main() {
    class Solution {
        fun smallestRepunitDivByK(k: Int): Int {
            if (k == 1) {
                return 1
            }

            var mod = 1

            var current = 1
            val visited = hashSetOf(1 to 1)

            var result = 1
            while (true) {
                current = (current * 10) % k
                mod = (mod + current) % k
                result++

                if (mod == 0) {
                    return result
                } else if (!visited.add(current to mod)) {
                    return -1
                }
            }
        }
    }

    expect {
        Solution().smallestRepunitDivByK(
            3
        )
    }
}