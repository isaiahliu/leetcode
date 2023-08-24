package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countVowelSubstrings(word: String): Int {
            val map = mapOf(
                'a' to LinkedList<Int>(),
                'e' to LinkedList<Int>(),
                'i' to LinkedList<Int>(),
                'o' to LinkedList<Int>(),
                'u' to LinkedList<Int>()
            )

            var result = 0
            var start = 0
            "$word ".forEachIndexed { index, c ->
                map[c]?.also {
                    it.add(index)
                } ?: run {
                    val end = index - 1

                    if (map.values.none { it.isEmpty() }) {
                        var left = start
                        var right = map.values.map { it.peek() }.max()

                        while (true) {
                            result += end - right + 1

                            val indexList = map[word[left]]!!

                            indexList.poll()

                            right = right.coerceAtLeast(indexList.peek() ?: break)
                            left++
                        }
                    }

                    start = index + 1
                    map.values.forEach { it.clear() }
                }
            }

            return result
        }
    }

    expect {
        Solution().countVowelSubstrings(
            "aeiou".repeat(10000)
        )
    }
}