package p26xx

import util.expect
import java.util.*

fun main() {
    class Graph(n: Int, edges: Array<IntArray>) {
        val adjacent = Array(n) { hashMapOf<Int, Int>() }

        init {
            edges.forEach { (from, to, cost) ->
                adjacent[from][to] = cost
            }
        }

        fun addEdge(edge: IntArray) {
            val (from, to, cost) = edge

            adjacent[from][to] = cost
        }

        fun shortestPath(node1: Int, node2: Int): Int {
            val visited = hashSetOf<Int>()

            val nodes = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            nodes += node1 to 0

            while (nodes.isNotEmpty()) {
                val (next, sum) = nodes.poll()

                when {
                    next == node2 -> {
                        return sum
                    }

                    visited.add(next) -> {
                        adjacent[next].forEach { (to, cost) ->
                            nodes += to to sum + cost
                        }
                    }
                }
            }

            return -1
        }
    }

    expect {
        Graph(1, arrayOf())
    }
}