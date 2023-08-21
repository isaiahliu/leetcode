package p08xx

import util.expect

fun main() {
    class Solution {
        fun reorderedPowerOf2(n: Int): Boolean {
            val length = n.toString().length

            var t = 1
            while (t.toString().length < length) {
                t *= 2
            }

            val set = hashSetOf<String>()

            while (t.toString().length == length) {
                set.add(String(t.toString().toCharArray().also { it.sort() }))
                t *= 2
            }

            return String(n.toString().toCharArray().also { it.sort() }) in set
        }
    }

    expect {
        Solution().reorderedPowerOf2(
            1
        )

    }
}