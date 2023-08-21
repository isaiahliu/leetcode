package p04xx

import util.expect

fun main() {
    class Solution {
        fun toHex(num: Int): String {
            var t = num.toLong()

            if (t < 0) {
                t += 1L shl 32
            }

            val result = StringBuilder()

            while (t > 0) {
                when (val d = (t % 16).toInt()) {
                    in 0..9 -> {
                        result.insert(0, '0' + d)
                    }

                    else -> {
                        result.insert(0, 'a' + (d - 10))
                    }
                }

                t /= 16
            }

            return result.toString().ifEmpty { "0" }
        }
    }

    expect {
        Solution().toHex(
            -1
        )
    }
}


