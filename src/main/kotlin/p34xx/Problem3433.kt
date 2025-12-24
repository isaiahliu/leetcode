package p34xx

import util.expect

fun main() {
    class Solution {
        fun countMentions(numberOfUsers: Int, events: List<List<String>>): IntArray {
            val result = IntArray(numberOfUsers)
            val offlines = IntArray(numberOfUsers) { -60 }

            events.map { it[1].toInt() to (it[0] to it[2]) }.sortedWith(compareBy<Pair<Int, Pair<String, String>>> { it.first }.thenByDescending { it.second.first })
                .forEach { (timestamp, p) ->
                    val (event, param) = p

                    when {
                        event == "OFFLINE" -> {
                            offlines[param.toInt()] = timestamp
                        }

                        param == "ALL" -> {
                            repeat(numberOfUsers) {
                                result[it]++
                            }
                        }

                        param == "HERE" -> {
                            repeat(numberOfUsers) {
                                if (offlines[it] + 60 <= timestamp) {
                                    result[it]++
                                }
                            }
                        }

                        else -> {
                            param.split(" ").forEach {
                                result[it.drop(2).toInt()]++
                            }
                        }
                    }
                }

            return result
        }
    }

    expect {
        Solution().countMentions(
            2, listOf(
                listOf("MESSAGE", "10", "id1 id0"),
                listOf("OFFLINE", "11", "0"),
                listOf("MESSAGE", "71", "HERE")

            )
        )
    }
}
