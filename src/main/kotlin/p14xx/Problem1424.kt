package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findDiagonalOrder(nums: List<List<Int>>): IntArray {
            val pq =
                PriorityQueue(compareByDescending<Pair<Int, Int>> { nums.lastIndex - it.first - it.second }.thenBy { it.second })

            nums.forEachIndexed { r, row ->
                row.forEachIndexed { c, _ ->
                    pq.add(r to c)
                }
            }

            return IntArray(pq.size) {
                pq.poll().let { (r, c) ->
                    nums[r][c]
                }
            }
        }
    }

    measureTimeMillis {
        Solution().findDiagonalOrder(
            listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9),
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

