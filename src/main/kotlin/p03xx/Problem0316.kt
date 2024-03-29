package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun removeDuplicateLetters(s: String): String {
            val lastIndices = IntArray(26)
            s.forEachIndexed { index, c ->
                lastIndices[c - 'a'] = index
            }

            val queue = LinkedList<Char>()

            loop@ for ((index, c) in s.withIndex()) {
                if (queue.isEmpty()) {
                    queue.push(c)
                } else {
                    val iter = queue.iterator()

                    var hasLower = false
                    var previousHigher = false
                    inner@ while (iter.hasNext()) {
                        when (val t = iter.next()) {
                            c -> {
                                if (hasLower && !previousHigher) {
                                    queue.remove(c)
                                } else {
                                    continue@loop
                                }
                                break@inner
                            }

                            else -> {
                                previousHigher = if (c > t) {
                                    hasLower = true
                                    false
                                } else {
                                    previousHigher || t > c && lastIndices[t - 'a'] < index
                                }
                            }
                        }
                    }
                    queue.push(c)
                }
            }

            return queue.reversed().joinToString("")
        }
    }

    expect {
        Solution().removeDuplicateLetters(
            "mitnlruhznjfyzmtmfnstsxwktxlboxutbic"
        )
    }
}

