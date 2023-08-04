package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun furthestBuilding(heights: IntArray, bricks: Int, ladders: Int): Int {
            var index = 1
            val laddersQueue = PriorityQueue<Int>()
            var brickRequirement = 0
            while (index < heights.size) {
                val diff = heights[index] - heights[index - 1]

                if (diff > 0) {
                    laddersQueue.add(diff)

                    if (laddersQueue.size > ladders) {
                        brickRequirement += laddersQueue.poll()

                        if (bricks < brickRequirement) {
                            break
                        }
                    }
                }

                index++
            }

            return index - 1
        }
    }

    measureTimeMillis {
        Solution().furthestBuilding(
            intArrayOf(15, 88), 5, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}