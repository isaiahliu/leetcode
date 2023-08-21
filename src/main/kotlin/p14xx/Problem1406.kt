package p14xx

import util.expect

fun main() {
    class Solution {
        fun stoneGameIII(stoneValue: IntArray): String {
            for (i in stoneValue.lastIndex - 1 downTo 0) {
                stoneValue[i] += stoneValue[i + 1]
            }

            val cache = IntArray(stoneValue.size) { Int.MIN_VALUE }

            fun dfs(index: Int): Int {
                if (index >= stoneValue.size) {
                    return 0
                }

                if (cache[index] > Int.MIN_VALUE) {
                    return cache[index]
                }

                var result = Int.MIN_VALUE

                val remainSum = stoneValue[index]

                (1..3).forEach {
                    result = result.coerceAtLeast(remainSum - dfs(index + it))
                }

                cache[index] = result
                return result
            }

            val total = stoneValue[0]

            val alice = dfs(0)

            return when {
                alice * 2 > total -> "Alice"
                alice * 2 < total -> "Bob"
                else -> "Tie"
            }
        }
    }

    expect {
        Solution().stoneGameIII(
            intArrayOf()
        )
    }
}

