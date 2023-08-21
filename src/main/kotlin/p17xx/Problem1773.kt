package p17xx

import util.expect

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

    expect {
        Solution().countMatches(
            listOf(), "", ""
        )
    }
}
