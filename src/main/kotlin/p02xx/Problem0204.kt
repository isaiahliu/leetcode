package p02xx

fun main() {
    class Solution {
        fun countPrimes(n: Int): Int {
            if (n < 3) {
                return 0
            }

            val flags = BooleanArray(n)

            var result = 0
            for (i in 2 until n) {
                if (!flags[i]) {
                    result++

                    var index = i * 2
                    while (index < flags.size) {
                        flags[index] = true
                        index += i
                    }
                }
            }

            return result
        }
    }

    println(
        Solution().countPrimes(
            10
        )
    )
}

