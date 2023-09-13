package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun mostProfitablePath(edges: Array<IntArray>, bob: Int, amount: IntArray): Int {
            val adjacent = Array(amount.size) { hashSetOf<Int>() }
            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            val bobSteps = IntArray(amount.size)

            var visited = hashSetOf(bob)
            val bobTasks = LinkedList<Int>()
            bobTasks.add(bob)
            var turn = 0

            while (bobTasks.isNotEmpty()) {
                turn++

                repeat(bobTasks.size) {
                    val node = bobTasks.poll()

                    bobSteps[node] = turn

                    adjacent[node].forEach {
                        if (visited.add(it)) {
                            bobTasks.add(it)
                        }
                    }
                }
            }

            val bobTurns = IntArray(amount.size) { Int.MAX_VALUE }
            var p = 0
            while (true) {
                val t = bobSteps[p]

                bobTurns[p] = t

                if (p == bob) {
                    break
                }
                adjacent[p].firstOrNull { bobSteps[it] == t - 1 }?.also {
                    p = it
                } ?: break
            }

            visited = hashSetOf(0)
            val tasks = LinkedList<Pair<Int, Int>>()
            tasks.add(0 to 0)

            var result = Int.MIN_VALUE

            turn = 0
            while (tasks.isNotEmpty()) {
                turn++

                repeat(tasks.size) {
                    var (node, sum) = tasks.poll()

                    val bobTurn = bobTurns[node]
                    when {
                        turn < bobTurn -> sum += amount[node]
                        turn == bobTurn -> sum += amount[node] / 2
                    }

                    var leaf = true

                    adjacent[node].forEach {
                        if (visited.add(it)) {
                            leaf = false

                            tasks.add(it to sum)
                        }
                    }

                    if (leaf) {
                        result = result.coerceAtLeast(sum)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().mostProfitablePath(
            arrayOf(
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 6),
                intArrayOf(2, 4),
                intArrayOf(3, 6),
                intArrayOf(3, 7),
                intArrayOf(5, 7)
            ), 4, intArrayOf(-6896, -1216, -1208, -1108, 1606, -7704, -9212, -8258)
        )
    }
}