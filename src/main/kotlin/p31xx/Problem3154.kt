package p31xx

import util.expect

fun main() {
    class Solution {
        fun waysToReachStair(k: Int): Int {
            val cache = hashMapOf<Pair<Pair<Int, Int>, Boolean>, Int>()
            fun dfs(current: Int, jump: Int, back: Boolean): Int {
                if (current > k + 1 || current < 0) {
                    return 0
                }

                val cacheKey = current to jump to back

                cache[cacheKey]?.also {
                    return it
                }

                var result = 0

                if (current == k) {
                    result++
                }

                result += dfs(current + (1 shl jump), jump + 1, false)

                if (!back) {
                    result += dfs(current - 1, jump, true)
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(1, 0, false)
        }
    }

    expect {
        Solution().waysToReachStair(
            0
        )
    }
}
