package p32xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun countGoodIntegers(n: Int, k: Int): Long {
            var fact = 1L
            val facts = LongArray(11) { num ->
                fact.also {
                    fact *= (num + 1)
                }
            }

            val infix = arrayOf(
                arrayOf(""),
                arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
            )
            var result = 0L

            var left = IntArray(n / 2) {
                1 - it.sign
            }

            val visited = hashSetOf<String>()
            loop@ while (true) {
                val prefix = left.joinToString("")
                var suffix = prefix.reversed()
                infix[n % 2].forEach {
                    val num = prefix + it + suffix

                    if (num.toLong() % k == 0L && visited.add(num.toCharArray().sortedArray().concatToString())) {
                        val counts = IntArray(10)
                        num.forEach {
                            counts[it - '0']++
                        }

                        var count = facts[n]
                        counts.forEach {
                            count /= facts[it]
                        }
                        result += count

                        if (counts[0] > 0) {
                            count = facts[n - 1]
                            counts[0]--
                            counts.forEach {
                                count /= facts[it]
                            }

                            result -= count
                        }
                    }
                }

                var point = left.lastIndex
                while (true) {
                    val num = left.getOrNull(point) ?: break@loop

                    if (num < 9) {
                        left[point++]++
                        break
                    } else {
                        point--
                    }
                }

                while (point < left.size) {
                    left[point++] = 0
                }
            }

            return result
        }
    }

    expect {
        Solution().countGoodIntegers(
            3, 5
        )
    }
}
