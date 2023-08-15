package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxHappyGroups(batchSize: Int, groups: IntArray): Int {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val filteredGroups = groups.map { it % batchSize }.filter { it > 0 }
            println(filteredGroups)
            var result = groups.size - filteredGroups.size
            var mask = (1 shl filteredGroups.size) - 1

            while (true) {
                val g = IntArray(batchSize)

                for ((index, num) in filteredGroups.withIndex()) {
                    if (mask and (1 shl index) > 0) {
                        val temp = IntArray(g.size) { g[it] }

                        for (gNum in 1 until g.size) {
                            if (g[gNum] > 0) {
                                val newSize = Integer.bitCount(g[gNum]) + 1
                                val targetNum = (gNum + num) % batchSize
                                val targetGroup = g[targetNum]

                                if ((g[0] == 0 || newSize < Integer.bitCount(g[0])) && (targetGroup == 0 || newSize < Integer.bitCount(
                                        targetGroup
                                    ))
                                ) {
                                    temp[targetNum] = g[gNum] + (1 shl index)
                                }
                            }
                        }

                        temp.forEachIndexed { tempIndex, tempStatus ->
                            if (tempStatus > 0 && (g[tempIndex] == 0 || Integer.bitCount(tempStatus) < Integer.bitCount(
                                    g[tempIndex]
                                ))
                            ) {
                                g[tempIndex] = tempStatus
                            }
                        }

                        g[num] = 1 shl index

                        if (Integer.bitCount(g[0]) == 1) {
                            break
                        }
                    }
                }


                if (g[0] > 0) {
                    result++
                    g[0].forEachBit {
                        print("${filteredGroups[it]}, ")
                        mask -= 1 shl it
                    }
                    println()
                } else {
                    break
                }
            }

            if (mask > 0) {
                result++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxHappyGroups(
            9, intArrayOf(
                1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 7
            )
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
