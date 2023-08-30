package p21xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minSteps(s: String, t: String): Int {
            val counts = IntArray(26)
            s.forEach {
                counts[it - 'a']++
            }

            t.forEach {
                counts[it - 'a']--
            }

            return counts.sumOf { it.absoluteValue }
        }
    }

    expect {
        Solution().minSteps(
            "", ""
        )
    }
}