package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
            return items.count { (type, color, name) ->
                when (ruleKey) {
                    "type" -> type
                    "color" -> color
                    "name" -> name
                    else -> null
                } == ruleValue
            }
        }
    }

    measureTimeMillis {
        Solution().countMatches(
            listOf(), "", ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
