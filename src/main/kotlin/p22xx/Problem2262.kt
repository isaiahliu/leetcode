package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun appealSum(s: String): Long {

            val indices = Array(27) {
                LinkedList<Int>()
            }

            s.forEachIndexed { index, c ->
                indices[c - 'a'].add(index)
            }

            indices[26].add(s.length)

            val tops = TreeSet<Int>(compareBy { indices[it].peek() })
            indices.forEachIndexed { index, list ->
                if (list.isNotEmpty()) {
                    tops.add(index)
                }
            }

            var result = 0L
            while (tops.isNotEmpty()) {
                var diffCount = 0
                tops.reduce { first, second ->
                    diffCount++

                    result += (indices[second].peek() - indices[first].peek()) * diffCount

                    second
                }

                tops.pollFirst()?.also {
                    indices[it].poll()

                    if (indices[it].isNotEmpty()) {
                        tops.add(it)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().appealSum(
            "abbca"
        )
    }
}