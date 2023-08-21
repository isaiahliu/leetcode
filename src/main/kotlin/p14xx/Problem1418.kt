package p14xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun displayTable(orders: List<List<String>>): List<List<String>> {
            val foods = TreeMap<String, MutableMap<Int, Int>>()
            val tables = TreeSet<Int>()

            orders.forEach { (_, table, food) ->
                val tableNum = table.toInt()
                tables.add(tableNum)

                foods.computeIfAbsent(food) { hashMapOf() }.also {
                    it[tableNum] = (it[tableNum] ?: 0) + 1
                }
            }

            val result = arrayListOf<List<String>>()

            result.add(listOf("Table") + foods.keys)

            tables.forEach {
                val details = arrayListOf(it.toString())

                foods.forEach { (_, map) ->
                    details.add((map[it] ?: 0).toString())
                }

                result.add(details)
            }

            return result
        }
    }

    expect {
        Solution().displayTable(
            listOf()
        )
    }
}

