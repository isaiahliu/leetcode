package p31xx

import util.expect

fun main() {
    class Solution {
        fun getSmallestString(s: String, k: Int): String {
            return buildString {
                var t = k
                s.forEach {
                    val distanceFromA = minOf(it - 'a', 'a' - it + 26)

                    when {
                        t == 0 -> {
                            append(it)
                        }

                        distanceFromA <= t -> {
                            append('a')
                            t -= distanceFromA
                        }

                        else -> {
                            append(it - t)
                            t = 0
                        }
                    }
                }
            }
        }
    }

    expect {
        Solution().getSmallestString(
            "1", 5
        )
    }
}
