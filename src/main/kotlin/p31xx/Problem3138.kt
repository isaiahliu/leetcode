package p31xx

import util.expect

fun main() {
    class Solution {
        fun minAnagramLength(s: String): Int {
            for (i in 1..s.length) {
                if (s.length % i == 0) {
                    val set = hashSetOf<String>()

                    for (index in s.indices step i) {
                        set.add(s.substring(index, index + i).toCharArray().also { it.sort() }.concatToString())

                        if (set.size > 1) {
                            break
                        }
                    }

                    if (set.size == 1) {
                        return i
                    }
                }
            }

            return s.length
        }
    }

    expect {
        Solution().minAnagramLength(
            ""
        )
    }
}
