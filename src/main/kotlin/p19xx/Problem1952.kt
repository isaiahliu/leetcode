package p19xx

import util.expect

fun main() {
    class Solution {
        fun isThree(n: Int): Boolean {
            val set = hashSetOf(1, n)
            for (i in 2..n / 2) {
                if (n % i == 0) {
                    set.add(i)
                    set.add(n / i)
                }

                if (set.size > 3) {
                    return false
                }
            }
            return set.size == 3
        }
    }

    expect {
        Solution().isThree(
            5
        )
    }
}