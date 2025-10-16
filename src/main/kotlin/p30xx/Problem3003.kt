package p30xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxPartitionsAfterOperations(s: String, k: Int): Int {
            if (k == 26) {
                return 1
            }

            var count = 1
            var mark = 1 shl (s[0] - 'a')

            val modifiedMarks = hashSetOf<Int>()

            repeat(26) {
                if (s[0] != 'a' + it) {
                    modifiedMarks += 1 shl it
                }
            }

            var result = 1
            for (i in 1 until s.length) {
                val ch = s[i]

                val map = TreeMap<Int, MutableSet<Int>>(compareByDescending { it })
                modifiedMarks.forEach { prevMark ->
                    if ((prevMark or (1 shl (ch - 'a'))).countOneBits() > k) {
                        map.computeIfAbsent(result + 1) { hashSetOf() } += 1 shl (ch - 'a')
                    } else {
                        map.computeIfAbsent(result) { hashSetOf() } += prevMark or (1 shl (ch - 'a'))
                    }
                }

                repeat(26) {
                    if (ch != 'a' + it) {
                        if ((mark or (1 shl it)).countOneBits() > k) {
                            map.computeIfAbsent(count + 1) { hashSetOf() } += 1 shl it
                        } else {
                            map.computeIfAbsent(count) { hashSetOf() } += mark or (1 shl it)
                        }
                    }
                }

                modifiedMarks.clear()
                map.pollFirstEntry().also {
                    result = it.key

                    modifiedMarks += it.value
                }

                if ((mark or (1 shl (ch - 'a'))).countOneBits() > k) {
                    count++
                    mark = 0
                }
                mark = mark or (1 shl (ch - 'a'))
            }

            return result
        }
    }

    expect {
        Solution().maxPartitionsAfterOperations(
            "aabcacc", 2
        )
    }
}
