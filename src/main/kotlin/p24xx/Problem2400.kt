package p24xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun numberOfWays(startPos: Int, endPos: Int, k: Int): Int {
            if ((endPos - startPos).absoluteValue > k || (startPos - endPos - k) and 1 == 1) {
                return 0
            } else {
                val m = 1000000007
                val mi = m.toBigInteger()

                fun Int.arrangement(start: Int = 1): Int {
                    var result = 1L

                    for (num in start..this) {
                        result *= num
                        result %= m
                    }

                    return result.toInt()
                }

                fun combine(cm: Int, cn: Int): Int {
                    val cmn = cm - cn

                    return ((cm.arrangement(cn.coerceAtLeast(cmn) + 1) * cn.coerceAtMost(cmn).arrangement()
                        .toBigInteger()
                        .modInverse(mi).toLong()) % m).toInt()
                }

                val distance = (endPos - startPos).absoluteValue

                val dir1 = (k - distance) / 2

                return combine(k, dir1)
            }
        }
    }

    expect {
        Solution().numberOfWays(
            5, 4, 2
        )
    }
}