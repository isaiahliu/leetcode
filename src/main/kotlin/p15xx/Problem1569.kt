package p15xx

import util.expect

fun main() {
    class Solution {
        fun numOfWays(nums: IntArray): Int {
            val m = 1000000007

            val matrix = Array(nums.size) {
                IntArray(nums.size) { 1 }
            }

            for (r in 1 until matrix.size) {
                for (c in 1 until matrix[r].size) {
                    matrix[r][c] = ((0L + matrix[r - 1][c] + matrix[r][c - 1]) % m).toInt()
                }
            }

            class Node(val num: Int) {
                var left: Node? = null
                var right: Node? = null
                var size = 1

                fun add(newNode: Node) {
                    size++
                    if (newNode.num < num) {
                        left?.add(newNode) ?: run {
                            left = newNode
                        }
                    } else {
                        right?.add(newNode) ?: run {
                            right = newNode
                        }
                    }
                }

                val possible: Int
                    get() {
                        var result = 1L

                        left?.possible?.also {
                            result *= it
                            result %= m
                        }

                        right?.possible?.also {
                            result *= it
                            result %= m
                        }

                        result *= matrix[left?.size ?: 0][right?.size ?: 0]
                        result %= m

                        return result.toInt()
                    }
            }

            val root = Node(nums[0])

            for (i in 1 until nums.size) {
                root.add(Node(nums[i]))
            }
            return root.possible - 1
        }
    }

    expect {
        Solution().numOfWays(
            intArrayOf(3, 1, 2, 5, 4, 6)
        )
    }
}

