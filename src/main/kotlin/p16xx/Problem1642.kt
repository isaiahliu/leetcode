package p16xx

import java.util.*
import util.expect

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

    expect {
        Solution().furthestBuilding(
            intArrayOf(15, 88), 5, 3
        )
    }
}