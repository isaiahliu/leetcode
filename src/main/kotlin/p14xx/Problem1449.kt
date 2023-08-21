package p14xx

import util.expect

fun main() {
    class Solution {
        fun largestNumber(cost: IntArray, target: Int): String {
            fun String?.greaterNum(target: String): String {
                return when {
                    this == null -> target
                    this.length > target.length -> this
                    this.length < target.length -> target
                    this > target -> this
                    else -> target
                }
            }

            val costMap = hashMapOf<Int, Char>()

            cost.forEachIndexed { index, i ->
                costMap[i] = '1' + index
            }

            val cache = hashMapOf<Int, String?>()
            fun dfs(remain: Int): String? {
                if (remain < 0) {
                    return null
                }

                if (remain == 0) {
                    return ""
                }

                if (remain in cache) {
                    return cache[remain]
                }

                var result: String? = null

                costMap.forEach { (cost, num) ->
                    dfs(remain - cost)?.also {
                        result = result.greaterNum("${num}${it}")
                    }
                }

                cache[remain] = result

                return result
            }

            return dfs(target) ?: "0"
        }
    }

    expect {
        Solution().largestNumber(
            intArrayOf(6, 10, 15, 40, 40, 40, 40, 40, 40), 47
        )

    }
}

