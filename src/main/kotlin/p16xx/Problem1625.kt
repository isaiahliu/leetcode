package p16xx

import util.expect

fun main() {
    class Solution {
        fun findLexSmallestString(s: String, a: Int, b: Int): String {
            val visited = hashSetOf(s)
            val tasks = hashSetOf(s)

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    val rotateStr = it.drop(b) + it.take(b)
                    if (visited.add(rotateStr)) {
                        tasks.add(rotateStr)
                    }

                    String(it.mapIndexed { index, c ->
                        if (index % 2 == 1) {
                            '0' + (c - '0' + a) % 10
                        } else {
                            c
                        }
                    }.toCharArray()).also {
                        if (visited.add(it)) {
                            tasks.add(it)
                        }
                    }
                }
            }

            return visited.min()
        }
    }

    expect {
        Solution().findLexSmallestString(
            "5525", 9, 2
        )
    }
}