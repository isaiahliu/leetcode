package p21xx

import util.expect
import java.util.*

fun main() {
    class SORTracker {
        val best = PriorityQueue(compareBy<Pair<String, Int>> { it.second }.thenByDescending { it.first })
        val queue = PriorityQueue(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first })

        fun add(name: String, score: Int) {
            best.add(name to score)

            queue.add(best.poll())
        }

        fun get(): String {
            val result = queue.poll()
            best.add(result)
            return result.first
        }
    }

    expect {
        SORTracker().add(
            "", 1
        )
    }
}