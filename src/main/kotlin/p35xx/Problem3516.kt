package p35xx

import util.expect
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    class Solution {
        fun findClosest(x: Int, y: Int, z: Int): Int {
            return (x - z).absoluteValue.compareTo((y - z).absoluteValue).let {
                ((it + 1) / 2 + 1) * it.sign.absoluteValue
            }
        }
    }

    expect {
        Solution().findClosest(
            1, 2, 3
        )
    }
}
