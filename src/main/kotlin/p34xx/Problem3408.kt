package p34xx

import util.expect
import java.util.*

fun main() {
    class TaskManager(tasks: List<List<Int>>) {
        val priorities = hashMapOf<Int, Int>()
        val userIds = hashMapOf<Int, Int>()
        val queue = TreeSet(compareByDescending<Int> { priorities[it] }.thenByDescending { it })

        init {
            tasks.forEach { (userId, taskId, priority) ->
                add(userId, taskId, priority)
            }
        }

        fun add(userId: Int, taskId: Int, priority: Int) {
            userIds[taskId] = userId
            priorities[taskId] = priority
            queue.add(taskId)
        }

        fun edit(taskId: Int, newPriority: Int) {
            queue.remove(taskId)
            priorities[taskId] = newPriority
            queue.add(taskId)
        }

        fun rmv(taskId: Int) {
            queue.remove(taskId)
        }

        fun execTop(): Int {
            return queue.pollFirst()?.let { userIds[it] } ?: -1
        }
    }

    expect {
        TaskManager(listOf())
    }
}
