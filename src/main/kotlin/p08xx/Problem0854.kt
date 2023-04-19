package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kSimilarity(s1: String, s2: String): Int {
            var result = 0

            val visited = hashSetOf(s1)

            val tasks = hashSetOf(s1)

            while (tasks.isNotEmpty()) {
                val goodTasks = hashSetOf<String>()
                val commonTasks = hashSetOf<String>()

                tasks.forEach { str ->
                    if (str == s2) {
                        return result
                    }


                    for (startIndex in str.indices) {
                        if (str[startIndex] == s2[startIndex]) {
                            continue
                        }

                        for (targetIndex in startIndex + 1 until str.length) {
                            if (str[targetIndex] == s2[targetIndex]) {
                                continue
                            }

                            var good = 0
                            if (str[startIndex] == s2[targetIndex]) {
                                good++
                            }
                            if (str[targetIndex] == s2[startIndex]) {
                                good++
                            }

                            if (good == 0) {
                                continue
                            }

                            val t = if (good == 2) goodTasks else commonTasks

                            val newStr =
                                "${str.take(startIndex)}${str[targetIndex]}${str.substring(startIndex + 1 until targetIndex)}${
                                    str[startIndex]
                                }${str.takeLast(str.lastIndex - targetIndex)}"

                            if (visited.add(newStr)) {
                                t.add(newStr)
                            }
                        }
                    }
                }

                result++
                tasks.clear()

                if (goodTasks.isEmpty()) {
                    tasks.addAll(commonTasks)
                } else {
                    tasks.addAll(goodTasks)
                }
            }

            return -1
        }
    }
    measureTimeMillis {
        Solution().kSimilarity(
            "abc", "bca"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}