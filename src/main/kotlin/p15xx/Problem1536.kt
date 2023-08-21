package p15xx

import util.expect

fun main() {
    class Solution {
        fun minSwaps(grid: Array<IntArray>): Int {
            grid.forEach {
                it[0] = 0
                for (i in it.lastIndex downTo 1) {
                    if (it[i] == 0) {
                        it[0]++
                    } else {
                        break
                    }
                }
            }

            var result = 0
            loop@ for (size in grid.size - 1 downTo 1) {
                for (s in grid) {
                    if (s[0] >= size) {
                        s[0] = -1
                        continue@loop
                    } else if (s[0] >= 0) {
                        result++
                    }
                }
                return -1
            }

            return result
        }
    }

    expect {
        Solution().minSwaps(
            arrayOf()
        )
    }
}

