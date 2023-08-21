package p07xx

import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun escapeGhosts(ghosts: Array<IntArray>, target: IntArray): Boolean {
            return ghosts.all { (x, y) ->
                (x - target[0]).absoluteValue + (y - target[1]).absoluteValue > target[0].absoluteValue + target[1].absoluteValue
            }
        }
    }

    expect {
        Solution().escapeGhosts(
            arrayOf(), intArrayOf()
        )
    }
}