package p04xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun smallestGoodBase(n: String): String {
            val num = n.toLong()

            val factors = arrayListOf<Long>()

            val visitedBase = hashSetOf<Long>()
            val queue = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })

            var t = num - 1
            var d = 2L
            while (d * d <= t) {
                if (t % d == 0L) {
                    factors.add(d)
                    t /= d
                } else {
                    d++
                }
            }

            if (t > 1) {
                factors.add(t)
            }

            factors.forEachIndexed { index, f ->
                if (visitedBase.add(f)) {
                    queue.offer(f to index)
                }
            }

            while (true) {
                val (base, index) = queue.poll()

                t = num

                while (t > 1 && t % base == 1L) {
                    t /= base
                }

                if (t == 1L) {
                    return base.toString()
                } else {
                    for (i in index + 1 until factors.size) {
                        val newBase = base * factors[i]

                        if (visitedBase.add(newBase)) {
                            queue.offer(newBase to i)
                        }
                    }
                }
            }
        }
    }

    println("231".toInt(5))
    measureTimeMillis {
        Solution().smallestGoodBase(
            "1000000000000000000"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}