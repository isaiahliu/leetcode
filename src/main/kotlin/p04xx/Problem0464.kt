package p04xx

import util.expect

fun main() {
    class Solution {
        fun canIWin(maxChoosableInteger: Int, desiredTotal: Int): Boolean {
            if (maxChoosableInteger >= desiredTotal) {
                return true
            }

            if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
                return false
            }

            val cache = hashMapOf<Int, Boolean>()

            fun canWin(status: Int = 0, sum: Int = 0): Boolean {
                if (status in cache) {
                    return cache[status] ?: false
                }

                var result = false

                for (i in 0 until maxChoosableInteger) {
                    val p = 1 shl i
                    val num = i + 1

                    if (status and p == 0) {
                        if (sum + num >= desiredTotal || !canWin(status + p, sum + num)) {
                            result = true
                            break
                        }
                    }
                }

                cache[status] = result

                return result
            }

            return canWin()
        }
    }

    expect {
        Solution().canIWin(
            10, 11
        )
    }
}