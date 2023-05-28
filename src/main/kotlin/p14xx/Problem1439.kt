package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kthSmallest(mat: Array<IntArray>, k: Int): Int {
            val queue = PriorityQueue<IntArray>(compareBy {
                it.mapIndexed { index, i -> mat[index][i] }.sum()
            })

            queue.add(IntArray(mat.size))

            val visited = hashSetOf<String>()
            repeat(k - 1) {
                val min = queue.poll()

                repeat(mat.size) {
                    if (min[it] < mat[it].lastIndex) {
                        val new = IntArray(min.size) { min[it] }
                        new[it]++

                        if (visited.add(new.joinToString(","))) {
                            queue.add(new)
                        }
                    }
                }
            }

            return queue.peek().mapIndexed { index, i -> mat[index][i] }.sum()
        }
    }

    measureTimeMillis {
        Solution().kthSmallest(
            arrayOf(
                intArrayOf(1, 3, 11),
                intArrayOf(2, 4, 6)
            ), 9
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}