package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun monotoneIncreasingDigits(n: Int): Int {
            val chars = n.toString().toCharArray()

            for (i in 0 until chars.lastIndex) {
                if (chars[i + 1] < chars[i]) {
                    chars[i]--
                    var t = i - 1
                    while (t >= 0) {
                        if (chars[t] > chars[t + 1]) {
                            chars[t--]--
                        } else {
                            break
                        }
                    }

                    for (j in t + 2 until chars.size) {
                        chars[j] = '9'
                    }

                    break
                }
            }

            return chars.joinToString("").toInt()
        }
    }

    measureTimeMillis {
        Solution().monotoneIncreasingDigits(
            120
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}