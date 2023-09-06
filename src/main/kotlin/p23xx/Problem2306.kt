package p23xx

import util.expect

fun main() {
    class Solution {
        fun distinctNames(ideas: Array<String>): Long {
            val map = hashMapOf<Char, MutableSet<String>>()

            ideas.forEach {
                map.computeIfAbsent(it[0]) { hashSetOf() }.add(it.drop(1))
            }

            val names = map.values.toTypedArray()

            var result = 0L
            for (i in names.indices) {
                val name1 = names[i]
                for (j in i + 1 until names.size) {
                    val name2 = names[j]

                    val dupCount = name1.count { it in name2 }

                    result += (name1.size - dupCount).toLong() * (name2.size - dupCount) * 2
                }
            }

            return result
        }
    }

    expect {
        Solution().distinctNames(
            arrayOf("coffee", "donuts", "time", "toffee")
        )
    }
}