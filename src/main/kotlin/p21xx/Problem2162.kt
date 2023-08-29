package p21xx

import util.expect

fun main() {
    class Solution {
        fun minCostSetTime(startAt: Int, moveCost: Int, pushCost: Int, targetSeconds: Int): Int {
            var minute = targetSeconds / 60
            var second = targetSeconds % 60

            var result = Int.MAX_VALUE

            fun cost() {
                if (minute > 99) {
                    return
                }

                val op = "$minute${second.toString().padStart(2, '0')}".trimStart('0')
                var pos = '0' + startAt

                var cost = 0
                op.forEach {
                    if (it != pos) {
                        cost += moveCost
                        pos = it
                    }

                    cost += pushCost
                }

                result = result.coerceAtMost(cost)
            }

            while (second < 100) {
                cost()

                second += 60
                minute--
            }

            return result
        }
    }

    expect {
        Solution().minCostSetTime(
            1, 2, 1, 600
        )
    }
}