package p09xx

import util.expect

fun main() {
    class Solution {
        fun leastOpsExpressTarget(x: Int, target: Int): Int {
            if (x == 1) {
                return target - 1
            }

            fun dfs(num: Int): Int {
                return when {
                    num < x -> {
                        (2 * num - 1).coerceAtMost((x - num) * 2)
                    }

                    num == 0 -> {
                        0
                    }

                    else -> {
                        var p = 1
                        var sum = x.toLong()
                        while (sum * x <= num) {
                            sum *= x
                            p++
                        }

                        var min = dfs(num - sum.toInt()) + p

                        if (sum * x - num < num) {
                            min = min.coerceAtMost(p + 1 + dfs(sum.toInt() * x - num))
                        }

                        min
                    }
                }

            }
            return dfs(target)
        }
    }

    expect {
        Solution().leastOpsExpressTarget(
            79,
            155800339
        )
    }
}
