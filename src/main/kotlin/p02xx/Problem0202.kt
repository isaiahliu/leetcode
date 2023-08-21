package p02xx

import util.expect

fun main() {
    class Solution {
        fun isHappy(n: Int): Boolean {
            val cache = hashSetOf(n)

            fun Int.nextNum(): Int {
                var result = 0

                var t = this
                while (t != 0) {
                    result += (t % 10).let { it * it }
                    t /= 10
                }

                return result
            }

            var t = n.nextNum()
            while (t != 1 && t !in cache) {
                cache.add(t)

                t = t.nextNum()
            }

            return t == 1
        }
    }

    expect {
        Solution().isHappy(
            1
        )
    }
}

