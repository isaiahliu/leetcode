package p06xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun leastInterval(tasks: CharArray, n: Int): Int {
            val map = TreeMap(tasks.toList().groupingBy { it }.eachCount().values.groupingBy { it }.eachCount())

            var result = 0
            while (map.isNotEmpty()) {
                var (num, count) = map.lastEntry()
                var queueSize = n + 1

                while (queueSize > 0) {
                    val processCount = queueSize.coerceAtMost(count)
                    queueSize -= processCount

                    result += processCount

                    val nextEntry = map.lowerEntry(num)

                    if (processCount == map[num]) {
                        map.remove(num)
                    } else {
                        map[num] = (map[num] ?: 0) - processCount
                    }

                    if (num > 1) {
                        map[num - 1] = (map[num - 1] ?: 0) + processCount
                    }

                    if (map.isEmpty()) {
                        return result
                    }

                    val (nextNum, nextCount) = nextEntry ?: break

                    num = nextNum
                    count = nextCount

                    if (nextNum == num - 1) {
                        count -= processCount
                    }
                }

                result += queueSize
            }

            return result
        }
    }

    expect {
        Solution().leastInterval(
            charArrayOf('A', 'B', 'A'), 2
        )

    }
}