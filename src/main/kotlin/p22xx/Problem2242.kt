package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumScore(scores: IntArray, edges: Array<IntArray>): Int {
            val adjacent = Array(scores.size) {
                intArrayOf(-1, -1, -1)
            }

            fun IntArray.fill(node: Int) {
                when {
                    this[0] == -1 -> this[0] = node
                    scores[node] > scores[this[0]] -> {
                        this[2] = this[1]
                        this[1] = this[0]
                        this[0] = node
                    }

                    this[1] == -1 -> this[1] = node
                    scores[node] > scores[this[1]] -> {
                        this[2] = this[1]
                        this[1] = node
                    }

                    this[2] == -1 || scores[node] > scores[this[2]] -> this[2] = node
                }
            }

            edges.forEach { (from, to) ->
                adjacent[from].fill(to)
                adjacent[to].fill(from)
            }

            var result = -1
            edges.forEach { (from, to) ->
                val fromAdj = adjacent[from]
                val toAdj = adjacent[to]

                for (l in fromAdj) {
                    when {
                        l < 0 -> {
                            break
                        }

                        l != to -> {
                            for (r in toAdj) {
                                when {
                                    r == -1 -> {
                                        break
                                    }

                                    r != from && r != l -> {
                                        result = result.coerceAtLeast(scores[l] + scores[r] + scores[from] + scores[to])
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return result
        }
    }
    expect {
        Solution().maximumScore(
            intArrayOf(5, 2, 9, 8, 4), arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(0, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 4)
            )
        )
    }
}