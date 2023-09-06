package p23xx

import util.expect

fun main() {
    class Solution {
        fun matchReplacement(s: String, sub: String, mappings: Array<CharArray>): Boolean {
            val map = hashMapOf<Char, MutableSet<Char>>()

            mappings.forEach { (from, to) ->
                map.computeIfAbsent(from) { hashSetOf() }.add(to)
            }
            loop@ for (offset in 0..s.length - sub.length) {
                var index = 0

                while (index < sub.length) {
                    when (s[offset + index]) {
                        sub[index] -> {
                            index++
                        }

                        in map[sub[index]].orEmpty() -> {
                            index++
                        }

                        else -> {
                            continue@loop
                        }
                    }
                }

                return true
            }

            return false
        }
    }

    expect {
        Solution().matchReplacement(
            "l3e7", "leet", arrayOf(
                charArrayOf('e', '3'),
                charArrayOf('t', '7'),
            )
        )
    }
}