package p25xx

import util.expect

fun main() {
    class Solution {
        fun distinctIntegers(n: Int): Int {
            val remain = hashSetOf(n)
            val result = hashSetOf(n)

            fun addFactor(num: Int) {
                if (result.add(num)) {
                    remain.add(num)
                }
            }
            while (remain.isNotEmpty()) {
                remain.toSet().also { remain.clear() }.forEach {
                    for (f in 2 until it) {
                        if (it % f == 1) {
                            addFactor(f)
                        }
                    }
                }
            }

            return result.size
        }
    }

    expect {
        Solution().distinctIntegers(
            3
        )
    }
}