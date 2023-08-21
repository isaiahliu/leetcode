package p04xx

import util.expect

fun main() {
    class Solution {
        fun getMaxRepetitions(s1: String, n1: Int, s2: String, n2: Int): Int {
            val cache = hashMapOf<Int, Int>()
            var index1 = 0
            var index2 = 0

            while (index1 / s1.length < n1) {
                if (s1[index1 % s1.length] == s2[index2 % s2.length]) {
                    index2++
                }
                index1++

                if (index1 % s1.length == 0) {
                    cache[index1 / s1.length] = index2 / s2.length

                    if (index2 % s2.length == 0) {
                        break
                    }
                }
            }

            val t = (index1 / s1.length)
            var result = n1 / (index1 / s1.length) * (index2 / s2.length)

            cache[n1 % t]?.also {
                result += it
            }

            return result / n2
        }
    }

    expect {
        Solution().getMaxRepetitions(
            "bacaba", 3, "abacab", 1
        )
    }
}