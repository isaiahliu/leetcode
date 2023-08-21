package p12xx

import util.expect

fun main() {
    class Solution {
        fun minFlips(mat: Array<IntArray>): Int {
            val m = mat.size
            val n = mat[0].size

            var initial = 0
            mat.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    initial += num * (1 shl (r * n + c))
                }
            }

            val visited = hashSetOf(initial)

            val tasks = hashSetOf(initial)

            var step = 0
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { status ->
                    if (status == 0) {
                        return step
                    }

                    repeat(m * n) {
                        val r = it / n
                        val c = it % n

                        var newStatus = status
                        arrayOf(r to c, r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                            it.first in 0 until m && it.second in 0 until n
                        }.forEach {
                            newStatus = newStatus xor (1 shl (it.first * n + it.second))
                        }

                        if (visited.add(newStatus)) {
                            tasks.add(newStatus)
                        }
                    }
                }

                step++
            }

            return -1
        }
    }

    expect {
        Solution().minFlips(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1)
            )
        )
    }
}
