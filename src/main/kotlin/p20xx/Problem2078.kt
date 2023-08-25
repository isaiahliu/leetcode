package p20xx

import util.expect

fun main() {
    class Solution {
        fun maxDistance(colors: IntArray): Int {
            return if (colors[0] != colors[colors.lastIndex]) {
                colors.lastIndex
            } else {
                colors.indexOfLast { it != colors[0] }
                    .coerceAtLeast(colors.lastIndex - colors.indexOfFirst { it != colors[0] })
            }
        }
    }

    expect {
        Solution().maxDistance(
            intArrayOf()
        )
    }
}