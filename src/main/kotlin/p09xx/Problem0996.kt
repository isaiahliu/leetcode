package p09xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun numSquarefulPerms(nums: IntArray): Int {
            nums.sort()

            fun isSquare(a: Int, b: Int): Boolean {
                return (a + b).let {
                    sqrt(it.toDouble()).toInt().let { it * it } == it
                }
            }

            val connections = hashMapOf<Int, MutableSet<Int>>()

            for (i in nums.indices) {
                for (j in i + 1 until nums.size) {
                    if (isSquare(nums[i], nums[j])) {
                        connections.computeIfAbsent(i) { hashSetOf() }.add(j)
                        connections.computeIfAbsent(j) { hashSetOf() }.add(i)
                    }
                }
            }

            var result = 0
            fun dfs(index: Int, route: Set<Int>) {
                if (route.size == nums.size) {
                    result++
                } else {
                    connections[index]?.forEach {
                        if (it !in route) {
                            if (nums[it] != nums.getOrNull(it - 1) || it - 1 in route) {
                                dfs(it, route + it)
                            }
                        }
                    }
                }
            }

            repeat(nums.size) {
                if (nums[it] != nums.getOrNull(it - 1)) {
                    dfs(it, setOf(it))
                }
            }

            return result
        }
    }

    expect {
        Solution().numSquarefulPerms(
            intArrayOf(2, 2, 2)
        )
    }
}
