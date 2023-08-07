package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun boxDelivering(boxes: Array<IntArray>, portsCount: Int, maxBoxes: Int, maxWeight: Int): Int {
            val cache = hashMapOf<Int, Int>()
            fun dfs(index: Int): Int {
                if (index == boxes.size) {
                    return 0
                }

                if (index in cache) {
                    return cache[index] ?: 0
                }

                val load = LinkedList<Int>()

                var lastPort = boxes[index][0]
                var portCount = 1
                var totalCount = 0
                var totalWeight = 0
                var i = index
                while (i < boxes.size && totalCount < maxBoxes && totalWeight + boxes[i][1] <= maxWeight) {
                    totalCount++
                    totalWeight += boxes[i][1]

                    if (lastPort != boxes[i][0]) {
                        lastPort = boxes[i][0]
                        portCount++
                    }

                    load.push(i++)
                }

                var result = portCount + 1 + dfs(i)

                boxes.getOrNull(i)?.get(0)?.also {
                    var popped = false
                    while (true) {
                        if (boxes[load.peek() ?: break][0] == it) {
                            popped = true
                            i = load.poll()
                        } else {
                            break
                        }
                    }

                    if (popped && load.isNotEmpty() && boxes[load.peek()][0] != it) {
                        result = result.coerceAtMost(portCount + dfs(i))
                    }
                }

                cache[index] = result
                return result
            }

            return dfs(0)
        }
    }

    measureTimeMillis {
        Solution().boxDelivering(
            arrayOf(
                intArrayOf(2, 4),//2

                intArrayOf(2, 5),//4

                intArrayOf(3, 1),
                intArrayOf(3, 2),//6

                intArrayOf(3, 7),//8

                intArrayOf(3, 1),
                intArrayOf(4, 4),//11

                intArrayOf(1, 3),
                intArrayOf(5, 2)//14

            ), 5, 5, 7
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

