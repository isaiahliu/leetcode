package p31xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun findPermutationDifference(s: String, t: String): Int {
            val sIndices = s.indices.associateBy { s[it] }

            return t.indices.sumOf {
                (it - (sIndices[t[it]] ?: 0)).absoluteValue
            }
        }
    }

    expect {
        Solution().findPermutationDifference(
            "", ""
        )
    }
}
