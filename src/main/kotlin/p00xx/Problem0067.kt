package p00xx

import util.expect

fun main() {
    class Solution {
        fun addBinary(a: String, b: String): String {
            val reverseA = a.reversed()
            val reverseB = b.reversed()

            val bits = IntArray(a.length.coerceAtLeast(b.length) + 1) {
                reverseA.getOrNull(it)?.let { it - '0' } ?: 0
            }

            for (i in 0 until bits.lastIndex) {
                val sum = bits[i] + (reverseB.getOrNull(i)?.let { it - '0' } ?: 0)

                bits[i] = sum % 2

                bits[i + 1] += sum / 2
            }

            return bits.joinToString("").reversed().trimStart('0').ifEmpty { "0" }
        }
    }

    expect {
        Solution().addBinary("11", "10")
    }
}

