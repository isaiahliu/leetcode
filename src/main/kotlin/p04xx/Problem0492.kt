package p04xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun constructRectangle(area: Int): IntArray {
            var w = sqrt(area.toDouble()).toInt()

            while (area % w != 0) {
                w--
            }

            return intArrayOf(area / w, w)
        }
    }

    expect {
        Solution().constructRectangle(
            122122
        )
    }
}