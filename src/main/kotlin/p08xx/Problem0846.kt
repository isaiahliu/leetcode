package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
            if (groupSize == 1) {
                return true
            }

            if (hand.size % groupSize != 0) {
                return false
            }

            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

            hand.sorted().forEach { card ->
                val top = queue.peek()?.first
                when {
                    top == null || card <= top -> queue.offer(card to 1)
                    card == top + 1 -> {
                        queue.poll()?.second?.also {
                            if (it + 1 < groupSize) {
                                queue.offer(card to it + 1)
                            }
                        }
                    }

                    else -> return false
                }
            }

            return queue.isEmpty()
        }
    }

    measureTimeMillis {
        Solution().isNStraightHand(
            intArrayOf(1, 2, 3, 6, 2, 3, 4, 7, 8), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}