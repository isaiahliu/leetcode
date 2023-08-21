package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun assignTasks(servers: IntArray, tasks: IntArray): IntArray {
            val result = IntArray(tasks.size)

            val idle = PriorityQueue(compareBy<Int> { servers[it] }.thenBy { it })
            idle.addAll(servers.indices)

            val busy = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

            var tasksIndex = 0

            var time = 0

            while (tasksIndex < tasks.size) {
                while (busy.isNotEmpty() && busy.peek().second <= time) {
                    idle.add(busy.poll().first)
                }

                while (tasksIndex <= time && tasksIndex < tasks.size && idle.isNotEmpty()) {
                    val server = idle.poll()
                    result[tasksIndex] = server
                    busy.add(server to time + tasks[tasksIndex])
                    tasksIndex++
                }

                if (idle.isNotEmpty()) {
                    time++
                } else {
                    time = busy.peek().second
                }
            }

            return result
        }
    }

    expect {
        Solution().assignTasks(
            intArrayOf(3, 3, 2), intArrayOf(1, 2, 3, 2, 1, 2)
        ).toList()
    }
}
