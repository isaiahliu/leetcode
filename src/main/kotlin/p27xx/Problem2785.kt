package p27xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun sortVowels(s: String): String {
            val vowels = "aeiouAEIOU".toSet()

            val remains = LinkedList<IntArray>()

            s.filter { it in vowels }.groupingBy { it }.eachCount().map { (ch, count) ->
                intArrayOf(ch.code, count)
            }.sortedBy { it[0] }.forEach {
                remains += it
            }

            return buildString {
                s.forEach {
                    if (it !in vowels) {
                        append(it)
                    } else {
                        remains.peek().also {
                            append(it[0].toChar())
                            if (--it[1] == 0) {
                                remains.poll()
                            }
                        }
                    }
                }
            }
        }
    }

    expect {
        Solution().sortVowels(
            ""
        )
    }
}
