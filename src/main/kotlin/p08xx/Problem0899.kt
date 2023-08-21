package p08xx

import util.expect

fun main() {
    class Solution {
        fun orderlyQueue(s: String, k: Int): String {
            return if (k == 1) {
                var min = s
                var t = s
                val visited = hashSetOf(s)

                while (true) {
                    t = t.drop(1) + t.take(1)

                    if (visited.add(t)) {
                        min = min.coerceAtMost(t)
                    } else {
                        break
                    }
                }
                min
            } else {
                String(s.toCharArray().also { it.sort() })
            }
        }
    }

    expect {
        Solution().orderlyQueue(
            "abcdefaaaa", 2
        )
    }
}