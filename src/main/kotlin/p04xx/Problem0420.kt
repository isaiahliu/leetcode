package p04xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun strongPasswordChecker(password: String): Int {
            val forceChars = hashSetOf<Char>()

            var pre = password[0]
            var joinCount = 0
            val joinCounts = PriorityQueue<Int>()
            password.forEach {
                when (it) {
                    in '0'..'9' -> forceChars.add('0')
                    in 'a'..'z' -> forceChars.add('a')
                    in 'A'..'Z' -> forceChars.add('A')
                }

                if (it == pre) {
                    joinCount++
                } else {
                    if (joinCount >= 3) {
                        joinCounts.add(joinCount)
                    }

                    joinCount = 1
                    pre = it
                }
            }

            if (joinCount >= 3) {
                joinCounts.add(joinCount)
            }

            var length = password.length
            var missingChars = 3 - forceChars.size

            var result = 0

            while (length > 20) {
                length--
                result++

                val t = joinCounts.firstOrNull { it % 3 == 0 } ?: joinCounts.firstOrNull { it % 3 == 1 }
                ?: joinCounts.firstOrNull { it % 3 == 2 }

                if (t != null) {
                    joinCounts.remove(t)

                    if (t > 3) {
                        joinCounts.add(t - 1)
                    }
                }
            }

            while (length < 6) {
                length++
                result++
                missingChars--

                if (joinCounts.isNotEmpty()) {
                    val t = joinCounts.poll()

                    if (t > 4) {
                        joinCounts.add(t - 2)
                    }
                }
            }

            while (joinCounts.isNotEmpty()) {
                val t = joinCounts.poll()
                missingChars--
                result++

                if (t > 5) {
                    joinCounts.add(t - 3)
                }
            }

            if (missingChars > 0) {
                result += missingChars
            }

            return result
        }
    }

    expect {
        Solution().strongPasswordChecker(
            "bbaaaaaaaaaaaaaaacccccc"
        )
    }
}


