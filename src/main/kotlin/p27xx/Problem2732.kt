package p27xx

import util.expect
import java.util.*
import kotlin.math.sign

fun main() {
    class Solution {
        fun goodSubsetofBinaryMatrix(grid: Array<IntArray>): List<Int> {
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

            val baseCounts = hashMapOf<Int, LinkedList<Int>>()

            grid.forEachIndexed { index, nums ->
                nums.mapIndexed { p, b ->
                    b * (1 shl p)
                }.sum().also {
                    baseCounts.computeIfAbsent(it) { LinkedList() }.add(index)
                }
            }

            fun tryFrom(firstKey: Int): List<Int>? {
                val counts = TreeMap<Int, LinkedList<Int>>(compareBy<Int> { it.countOneBits() }.thenBy { it })
                baseCounts.forEach { (k, v) ->
                    counts.computeIfAbsent(k) { LinkedList() }.addAll(v)
                }

                fun pop(key: Int): Int {
                    return counts[key]?.let { list ->
                        list.poll()?.also {
                            if (list.isEmpty()) {
                                counts.remove(key)
                            }
                        }
                    } ?: 0
                }

                val result = TreeSet<Int>()
                result += pop(firstKey)
                val ones = IntArray(grid[0].size) {
                    ((1 shl it) and firstKey).sign
                }
                var size = 1

                var invalid = ones.mapIndexed { index, i ->
                    if (i * 2 <= size) {
                        0
                    } else {
                        1 shl index
                    }
                }.sum()

                while (invalid > 0) {
                    val key = counts.keys.firstOrNull {
                        it and invalid == 0
                    } ?: return null

                    result += pop(key)

                    size++

                    key.forEachBit { p ->
                        ones[p]++
                    }

                    invalid = ones.mapIndexed { index, i ->
                        if (i * 2 <= size) {
                            0
                        } else {
                            1 shl index
                        }
                    }.sum()
                }

                return result.toList()
            }

            baseCounts.keys.forEach {
                tryFrom(it)?.also {
                    return it
                }
            }

            return emptyList()
        }
    }

    expect {
        Solution().goodSubsetofBinaryMatrix(
            arrayOf(
                intArrayOf(1, 1, 0, 0, 1),
                intArrayOf(0, 1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 0, 0),
                intArrayOf(0, 0, 0, 1, 0),
                intArrayOf(1, 0, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 1),
                intArrayOf(1, 1, 0, 1, 0),
                intArrayOf(1, 0, 1, 0, 1),
                intArrayOf(1, 1, 1, 1, 1)
            )
        )
    }
}
