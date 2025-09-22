package p35xx

import util.expect
import java.util.*

fun main() {
    class Router(private val memoryLimit: Int) {
        val visited = hashSetOf<Pair<Pair<Int, Int>, Int>>()
        val queue = LinkedList<IntArray>()

        val indices = hashMapOf<Int, Int>()
        val messages = hashMapOf<Int, MutableList<Pair<Int, Int>>>()

        fun addPacket(source: Int, destination: Int, timestamp: Int): Boolean {
            return if (visited.add(source to destination to timestamp)) {
                queue.add(intArrayOf(source, destination, timestamp))

                messages.computeIfAbsent(destination) { arrayListOf() } += timestamp to source

                if (queue.size > memoryLimit) {
                    forwardPacket()
                }

                true
            } else {
                false
            }
        }

        fun forwardPacket(): IntArray {
            return queue.pollFirst()?.also { (source, destination, timestamp) ->
                indices[destination] = (indices[destination] ?: 0) + 1
                visited -= source to destination to timestamp
            } ?: intArrayOf()
        }

        fun getCount(destination: Int, startTime: Int, endTime: Int): Int {
            val destinationMessages = messages[destination] ?: return 0

            var left = indices[destination] ?: 0
            var right = destinationMessages.lastIndex

            var start = Int.MAX_VALUE
            while (left <= right) {
                val mid = (left + right) / 2

                if (destinationMessages[mid].first >= startTime) {
                    start = mid
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            if (start == Int.MAX_VALUE) {
                return 0
            }
            left = indices[destination] ?: 0
            right = destinationMessages.lastIndex

            var end = -1
            while (left <= right) {
                val mid = (left + right) / 2

                if (destinationMessages[mid].first <= endTime) {
                    end = mid
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }

            return maxOf(end - start + 1, 0)
        }
    }

    expect {
        Router(1)
    }
}
