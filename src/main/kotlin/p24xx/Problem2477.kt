package p24xx

import util.expect
import java.util.*
import kotlin.math.sign

fun main() {
    class Solution {
        fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
            val degrees = IntArray(roads.size + 1)

            val adjacent = Array(roads.size + 1) { hashSetOf<Int>() }
            roads.forEach { (from, to) ->
                degrees[from]++
                degrees[to]++

                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            degrees[0] = roads.size + 2
            adjacent[0].clear()

            val queue = LinkedList<Int>()

            degrees.forEachIndexed { index, degree ->
                if (degree == 1) {
                    queue.add(index)
                }
            }

            val persons = IntArray(roads.size + 1)
            var result = 0L
            while (queue.isNotEmpty()) {
                val city = queue.poll()
                val person = persons[city] + 1
                degrees[city]--
                adjacent[city].forEach {
                    if (degrees[it] > 0) {
                        degrees[it]--

                        result += person / seats + (person % seats).sign

                        persons[it] += person
                        if (degrees[it] == 1) {
                            queue.add(it)
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumFuelCost(
            arrayOf(
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(1, 0),
                intArrayOf(0, 4),
                intArrayOf(0, 5),
                intArrayOf(4, 6)
            ), 2
        )
    }
}
