package p23xx

import util.expect

fun main() {
    class Solution {
        fun minimumNumbers(num: Int, k: Int): Int {
            return if (num == 0) {
                0
            } else {
                val visited = hashSetOf(k)
                var result = 1
                var sum = k

                while (num % 10 != sum % 10) {
                    sum += k
                    result++
                    if (sum > num || !visited.add(sum % 10)) {
                        return -1
                    }
                }

                result
            }
        }
    }

    expect {
        Solution().minimumNumbers(
            58, 9
        )
    }
}