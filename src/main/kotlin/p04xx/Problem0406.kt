package p04xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
            val map = TreeMap<Int, SortedSet<Int>>()

            people.forEach { (key, value) ->
                map.computeIfAbsent(key) { sortedSetOf() }.add(value)
            }

            val result = arrayListOf<IntArray>()

            fun process(preHeight: Int, requireCount: Int) {
                var processedCount = 0

                var t = preHeight
                while (processedCount < requireCount) {
                    val (height, s) = map.higherEntry(t)

                    val c = s.first()
                    val higherCount = result.count { it[0] >= height }

                    if (c - higherCount + 1 > requireCount - processedCount) {
                        t = height
                        continue
                    }

                    if (s.size == 1) {
                        map.remove(height)
                    } else {
                        s.remove(c)
                    }


                    if (c > higherCount) {
                        process(height, c - higherCount)
                    }
                    result.add(intArrayOf(height, c))
                    processedCount += c - higherCount + 1
                }
            }

            process(map.firstKey() - 1, people.size)

            return result.toTypedArray()
        }
    }

    expect {
        Solution().reconstructQueue(
            arrayOf(
                intArrayOf(7, 0),
                intArrayOf(4, 4),
                intArrayOf(7, 1),
                intArrayOf(5, 0),
                intArrayOf(6, 1),
                intArrayOf(5, 2)
            )
        )
    }
}


