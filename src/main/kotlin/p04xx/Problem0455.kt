package p04xx

import util.expect

fun main() {
    class Solution {
        fun findContentChildren(g: IntArray, s: IntArray): Int {
            g.sort()
            s.sort()

            var gIndex = 0
            var sIndex = 0

            var result = 0
            while (gIndex < g.size && sIndex < s.size) {
                if (s[sIndex++] >= g[gIndex]) {
                    result++
                    gIndex++
                }
            }

            return result
        }
    }

    expect {
        Solution().findContentChildren(intArrayOf(), intArrayOf())
    }
}