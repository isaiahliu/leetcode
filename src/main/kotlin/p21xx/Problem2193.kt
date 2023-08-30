package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minMovesToMakePalindrome(s: String): Int {
            val counts = Array(26) {
                LinkedList<Int>()
            }

            s.forEachIndexed { index, c ->
                counts[c - 'a'].add(index)
            }

            val indices = TreeSet<Int>()
            indices.addAll(s.indices)

            var leftIndex = 0
            var processed = 0
            var result = 0
            while (processed < s.length) {
                val c = s[leftIndex] - 'a'

                when {
                    counts[c].size == 1 -> {
                        counts[c].clear()
                        processed++
                        indices.remove(leftIndex)
                        result += (s.length - processed) / 2
                    }

                    leftIndex in indices -> {
                        val list = counts[c]
                        indices.remove(list.poll())
                        list.pollLast().also {
                            indices.remove(it)
                            result += indices.tailSet(it).size
                        }

                        processed += 2
                    }
                }

                leftIndex++
            }

            return result
        }
    }

    expect {
        Solution().minMovesToMakePalindrome(
            "letelt"
        )
    }
}