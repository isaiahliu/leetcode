package p18xx

import util.expect

fun main() {
    class Solution {
        fun evaluate(s: String, knowledge: List<List<String>>): String {
            val knowledgeMap = knowledge.associate { it[0] to it[1] }

            return "\\((\\w+)\\)".toRegex().replace(s) {
                knowledgeMap[it.groupValues[1]] ?: "?"
            }
        }
    }

    expect {
        Solution().evaluate(
            "(name)is(age)yearsold", listOf(
                listOf("name", "bob"),
                listOf("age", "two"),
            )
        )
    }
}
