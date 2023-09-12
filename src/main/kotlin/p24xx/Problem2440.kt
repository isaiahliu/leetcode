package p24xx

import util.expect

fun main() {
    class Solution {
        fun componentValue(nums: IntArray, edges: Array<IntArray>): Int {
            val adjacent = Array(nums.size) { hashSetOf<Int>() }
            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            var sum = 0L
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE

            nums.forEach {
                sum += it
                min = min.coerceAtMost(it)
                max = max.coerceAtLeast(it)
            }

            if (min == max) {
                return edges.size
            }
            var subSum = max

            fun dfs(node: Int, target: Int, visited: MutableSet<Int>): Int? {
                var amount = nums[node]
                adjacent[node].forEach {
                    if (visited.add(it)) {
                        amount += dfs(it, target, visited) ?: return null
                    }
                }

                return when {
                    amount > target -> null
                    amount == target -> 0
                    else -> amount
                }
            }

            while (subSum + subSum <= sum) {
                if (sum % subSum == 0L) {
                    if (dfs(0, subSum, mutableSetOf(0)) == 0) {
                        return (sum / subSum - 1).toInt()
                    }
                }
                subSum++
            }

            return 0
        }
    }

    expect {
        Solution().componentValue(
            intArrayOf(6, 2, 2, 2, 6), arrayOf(
                intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(3, 4)
            )
        )
    }
}