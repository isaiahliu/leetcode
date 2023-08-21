package p11xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun invalidTransactions(transactions: Array<String>): List<String> {
            val result = hashSetOf<Int>()

            val map = hashMapOf<String, LinkedList<Pair<Int, Pair<Int, String>>>>()

            transactions.mapIndexedNotNull { index, s ->
                val (name, time, amount, city) = s.split(",")

                if (amount.toInt() > 1000) {
                    result.add(index)
                }
                (index to name) to (time.toInt() to city)
            }.sortedBy { it.second.first }.forEach { (p1, p2) ->
                val (index, name) = p1
                val (time, city) = p2

                val txs = map.computeIfAbsent(name) { LinkedList() }

                for ((targetIndex, tp) in txs) {
                    val (targetTime, targetCity) = tp

                    if (time - targetTime > 60) {
                        break
                    } else if (city != targetCity) {
                        result.add(index)
                        result.add(targetIndex)
                    }
                }

                txs.push(index to (time to city))
            }

            return result.map { transactions[it] }
        }
    }

    expect {
        Solution().invalidTransactions(
            arrayOf(
                "bob,175,221,amsterdam",
                "bob,689,1910,barcelona",
                "bob,820,596,bangkok",
                "bob,832,1726,barcelona",
            )
        )
    }
}