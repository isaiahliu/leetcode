package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun evaluate(s: String, knowledge: List<List<String>>): String {
            val knowledgeMap = knowledge.associate { it[0] to it[1] }

            return "\\((\\w+)\\)".toRegex().replace(s) {
                knowledgeMap[it.groupValues[1]] ?: "?"
            }
        }
    }

    measureTimeMillis {
        Solution().evaluate(
            "(name)is(age)yearsold", listOf(
                listOf("name", "bob"),
                listOf("age", "two"),
            )
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
