package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun winnerSquareGame(n: Int): Boolean {
            val squareNums = arrayListOf<Int>()

            var t = 1
            while (t * t <= n) {
                squareNums.add(t * t)
                t++
            }

            val cache = hashMapOf<Int, Boolean>()
            fun dfs(remain: Int): Boolean {
                if (remain == 0) {
                    return false
                }

                if (remain in cache) {
                    return cache[remain] ?: false
                }

                var result = false

                for (squareNum in squareNums) {
                    if (squareNum <= remain) {
                        if (!dfs(remain - squareNum)) {
                            result = true
                            break
                        }
                    } else {
                        break
                    }
                }

                cache[remain] = result

                return result
            }

            return dfs(n)
        }
    }

    measureTimeMillis {
        Solution().winnerSquareGame(
            4
        ).also { println(it) }
    }
}

