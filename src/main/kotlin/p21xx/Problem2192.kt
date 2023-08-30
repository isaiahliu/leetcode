package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun getAncestors(n: Int, edges: Array<IntArray>): List<List<Int>> {
            val degrees = IntArray(n)

            val children = Array(n) {
                hashSetOf<Int>()
            }

            edges.forEach { (from, to) ->
                children[from].add(to)
                degrees[to]++
            }

            val result = Array(n) { mutableSetOf<Int>() }

            val queue = LinkedList<Int>()

            degrees.forEachIndexed { index, d ->
                if (d == 0) {
                    queue.add(index)
                }
            }

            while (queue.isNotEmpty()) {
                val from = queue.poll()

                val p = result[from]
                children[from].forEach {
                    result[it].addAll(p)
                    result[it].add(from)

                    degrees[it]--

                    if (degrees[it] == 0) {
                        queue.add(it)
                    }
                }
            }

            return result.map { it.sorted() }
        }
    }

    expect {
        Solution().getAncestors(
            1, arrayOf()
        )
    }
}