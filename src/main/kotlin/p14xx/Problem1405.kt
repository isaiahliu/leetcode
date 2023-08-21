package p14xx

import util.expect

fun main() {
    class Solution {
        fun longestDiverseString(a: Int, b: Int, c: Int): String {
            val array = arrayOf(intArrayOf(0, a), intArrayOf(1, b), intArrayOf(2, c))

            array.sortWith(compareByDescending { it[1] })

            var last = -1

            val result = StringBuilder()

            while (array[0][1] > 0) {
                var idx = 0
                var count = 2

                if (array[0][0] == last) {
                    if (array[1][1] > 0) {
                        idx++
                        count--
                    } else {
                        break
                    }
                }

                repeat(array[idx][1].coerceAtMost(count)) {
                    result.append('a' + array[idx][0])
                    array[idx][1]--
                }

                last = array[idx][0]

                array.sortWith(compareByDescending { it[1] })
            }

            return result.toString()
        }
    }

    expect {
        Solution().longestDiverseString(
            1, 2, 3
        )
    }
}

