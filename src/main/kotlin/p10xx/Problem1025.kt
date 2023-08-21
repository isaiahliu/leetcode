package p10xx

import util.expect

fun main() {
    class Solution {
        val cache = hashMapOf<Int, Boolean>()

        fun divisorGame(n: Int): Boolean {
            if (n in cache) {
                return cache[n] ?: false
            }

            var result = false
            for (i in n / 2 downTo 1) {
                if (n % i == 0 && !divisorGame(n - i)) {
                    result = true
                    break
                }
            }

            cache[n] = result

            return result
        }
    }

    expect {
        Solution().divisorGame(
            4
        )
    }
}
