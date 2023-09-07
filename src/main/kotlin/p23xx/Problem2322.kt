package p23xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minimumScore(nums: IntArray, edges: Array<IntArray>): Int {
            val adjacent = Array(nums.size) { hashSetOf<Int>() }
            val parents = IntArray(nums.size) { -2 + it.sign }
            val result = Array(nums.size) { IntArray(nums.size) { Int.MAX_VALUE } }
            val sums = IntArray(nums.size) { -1 }
            val rootSum = nums.fold(0) { a, b -> a xor b }

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            fun dfs(index: Int): Pair<Int, Set<Int>> {
                var sum = nums[index]

                val children = hashSetOf<Int>()
                adjacent[index].forEach { child ->
                    if (parents[child] == -1) {
                        parents[child] = index

                        dfs(child).also { (childSum, nodes) ->
                            sum = sum xor childSum
                            children.addAll(nodes)
                        }
                    }
                }

                sums[index] = sum

                if (parents[index] >= 0) {
                    children.forEach { child ->
                        val rootRemain = rootSum xor sum
                        val parentRemain = sum xor sums[child]
                        val childRemain = sums[child]

                        (maxOf(rootRemain, parentRemain, childRemain) - minOf(
                            rootRemain,
                            parentRemain,
                            childRemain
                        )).also {
                            result[index][child] = it
                            result[child][index] = it
                        }
                    }
                }

                return sum to children + index
            }

            dfs(0)

            for (edgeIndex1 in edges.indices) {
                val (from1, to1) = edges[edgeIndex1]
                val subRoot1 = if (parents[from1] == to1) from1 else to1

                for (edgeIndex2 in edgeIndex1 + 1 until edges.size) {
                    val (from2, to2) = edges[edgeIndex2]
                    val subRoot2 = if (parents[from2] == to2) from2 else to2

                    if (result[subRoot1][subRoot2] == Int.MAX_VALUE) {
                        val child1 = sums[subRoot1]
                        val child2 = sums[subRoot2]
                        val rootRemain = rootSum xor child1 xor child2

                        (maxOf(rootRemain, child1, child2) - minOf(
                            rootRemain,
                            child1,
                            child2
                        )).also {
                            result[subRoot1][subRoot2] = it
                            result[subRoot2][subRoot1] = it
                        }
                    }
                }
            }

            return result.minOf { it.min() }
        }
    }

    expect {
        Solution().minimumScore(
            intArrayOf(1, 5, 5, 4, 11), arrayOf(
                intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(3, 4)
            )
        )
    }
}