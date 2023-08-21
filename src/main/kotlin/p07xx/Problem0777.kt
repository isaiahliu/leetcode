package p07xx

import util.expect

fun main() {
    class Solution {
        fun canTransform(start: String, end: String): Boolean {
            if (start.length != end.length) {
                return false
            }

            val l = start.mapIndexed { index, c -> c to index }.filter { it.first != 'X' }
            val r = end.mapIndexed { index, c -> c to index }.filter { it.first != 'X' }

            if (l.size != r.size) {
                return false
            }

            var index = 0
            while (index < l.size) {
                val (lc, li) = l[index]
                val (rc, ri) = r[index++]

                when {
                    lc != rc -> return false
                    lc == 'L' && li < ri -> return false
                    lc == 'R' && li > ri -> return false
                }
            }

            return true
        }
    }

    expect {
        Solution().canTransform(
            "", ""
        )
    }
}