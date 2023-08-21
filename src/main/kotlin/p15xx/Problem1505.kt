package p15xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minInteger(num: String, k: Int): String {
            val charIndices = Array(10) { LinkedList<Int>() }
            num.forEachIndexed { index, c ->
                charIndices[c - '0'].add(index)
            }

            val leadIndices = LinkedList<Int>()

            val used = TreeSet<Int>()

            var index = 0
            var remain = k
            loop@ while (index < num.length && remain > 0) {
                if (index in used) {
                    index++
                    continue@loop
                }
                for (n in 0 until num[index] - '0') {
                    val indices = charIndices[n]
                    while (indices.isNotEmpty() && indices.peek() < index) {
                        indices.poll()
                    }

                    val targetIndex = indices.peek()
                    if (targetIndex != null) {

                        val skipCount = used.subSet(index, targetIndex).size
                        if (targetIndex - remain - skipCount <= index) {
                            indices.poll()
                            leadIndices.add(targetIndex)
                            used.add(targetIndex)

                            remain -= targetIndex - index - skipCount
                            continue@loop
                        }
                    }
                }

                leadIndices.add(index)
                used.add(index)
                index++
            }

            val result = StringBuilder()
            leadIndices.forEach {
                result.append(num[it])
            }

            num.forEachIndexed { i, c ->
                if (i !in used) {
                    result.append(c)
                }
            }

            return result.toString()
        }
    }

    expect {
        Solution().minInteger(
            "3142",
            4
        )
    }
}

