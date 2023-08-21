package p04xx

import util.expect

fun main() {
    class Solution {
        fun findKthNumber(n: Int, k: Int): Int {
            val special = hashSetOf<Int>()
            var t = n / 10
            while (t > 0) {
                special.add(t)
                t /= 10
            }

            var result = 1
            t = k - 1

            var treeSize = n.toString().map { "1" }.joinToString("").toInt() - 1

            while (t > 0) {
                when {
                    result == n -> {
                        result /= 10
                    }

                    result in special -> {
                        special.remove(result)

                        result *= 10
                        t--

                        treeSize /= 10
                        treeSize--
                    }

                    t > treeSize -> {
                        t -= treeSize

                        while (result % 10 == 9) {
                            result /= 10
                            treeSize++
                            treeSize *= 10
                        }

                        result++
                        t--
                    }

                    else -> {
                        result *= 10
                        treeSize /= 10
                        treeSize--

                        t--
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().findKthNumber(
            10,
            3
        )
    }
}


