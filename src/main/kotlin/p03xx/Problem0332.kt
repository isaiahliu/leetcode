package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun findItinerary(tickets: List<List<String>>): List<String> {
            val ticketMap = sortedMapOf<String, SortedMap<String, MutableList<Int>>>()

            tickets.forEachIndexed { index, (from, to) ->
                ticketMap.computeIfAbsent(from) { sortedMapOf() }.computeIfAbsent(to) { arrayListOf() }.add(index)
            }

            val result = arrayListOf("JFK")
            fun dfs(from: String, route: List<Int>) {
                if (result.size > 1) {
                    return
                }

                if (route.size == tickets.size) {
                    route.forEach { index ->
                        result.add(tickets[index][1])
                    }

                    return
                }

                ticketMap[from]?.forEach { (to, i) ->
                    i.forEach {
                        if (it !in route) {
                            dfs(to, route + it)
                        }
                    }
                }
            }

            ticketMap["JFK"]?.forEach { (to, i) ->
                i.forEach {
                    dfs(to, listOf(it))
                }
            }

            return result
        }
    }

    expect {
        Solution().findItinerary(
            listOf(
                listOf("EZE", "AXA"),
                listOf("TIA", "ANU"),
                listOf("ANU", "JFK"),
                listOf("JFK", "ANU"),
                listOf("ANU", "EZE"),
                listOf("TIA", "ANU"),
                listOf("AXA", "TIA"),
                listOf("TIA", "JFK"),
                listOf("ANU", "TIA"),
                listOf("JFK", "TIA")
            )
        )
    }
}

