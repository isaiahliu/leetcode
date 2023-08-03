package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun busiestServers(k: Int, arrival: IntArray, load: IntArray): List<Int> {
            if (arrival.size <= k) {
                return arrival.indices.toList()
            } else {
                val result = arrayListOf<Int>()
                val idle = TreeSet<Int>()

                repeat(k) {
                    idle.add(it)
                }

                val inProgress = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

                val weight = IntArray(k)
                arrival.forEachIndexed { index, time ->
                    while (inProgress.isNotEmpty() && inProgress.peek().first <= time) {
                        idle.add(inProgress.poll().second)
                    }

                    (idle.ceiling(index % k) ?: idle.firstOrNull())?.also {
                        weight[it]++
                        idle.remove(it)

                        inProgress.offer(time + load[index] to it)
                    }
                }

                var max = weight[0]
                weight.forEachIndexed { index, l ->
                    if (l > max) {
                        max = l
                        result.clear()
                    }

                    if (l == max) {
                        result.add(index)
                    }
                }
                return result
            }
        }
    }
    measureTimeMillis {
        Solution().busiestServers(
            3, intArrayOf(1, 2, 3, 4, 5), intArrayOf(5, 2, 3, 3, 3)
        ).also { println("${it} should be ${it}") }
    }
}

