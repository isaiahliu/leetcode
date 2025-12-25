package p35xx

import util.expect

fun main() {
    class Solution {
        fun maxProfit(n: Int, present: IntArray, future: IntArray, hierarchy: Array<IntArray>, budget: Int): Int {
            val childrens = hashMapOf<Int, MutableList<Int>>()

            hierarchy.forEach { (p, c) ->
                childrens.computeIfAbsent(p - 1) { arrayListOf() } += c - 1
            }

            fun merge(map1: Map<Int, Int>, map2: Map<Int, Int>, newMap: MutableMap<Int, Int>) {
                map1.forEach { (cost1, profit1) ->
                    map2.forEach { (cost2, profit2) ->
                        val newCost = cost1 + cost2

                        if (newCost <= budget) {
                            newMap[newCost] = maxOf((newMap[newCost] ?: Int.MIN_VALUE), profit1 + profit2)
                        }
                    }
                }
            }

            //ignore -- price -- halfPrice
            fun dfs(index: Int): Array<Map<Int, Int>> {
                var ignoreMap = hashMapOf(0 to 0)
                var priceMap = hashMapOf<Int, Int>()
                if (present[index] <= budget) {
                    priceMap[present[index]] = future[index] - present[index]
                }
                var halfPriceMap = hashMapOf<Int, Int>()
                if (present[index] / 2 <= budget) {
                    halfPriceMap[present[index] / 2] = future[index] - present[index] / 2
                }

                childrens[index]?.forEach {
                    val (childIgnoreMap, childPriceMap, childHalfPriceMap) = dfs(it)

                    val newIgnoreMap = hashMapOf<Int, Int>()
                    val newPriceMap = hashMapOf<Int, Int>()
                    val newHalfPriceMap = hashMapOf<Int, Int>()

                    merge(ignoreMap, childIgnoreMap, newIgnoreMap)
                    merge(ignoreMap, childPriceMap, newIgnoreMap)
                    merge(priceMap, childIgnoreMap, newPriceMap)
                    merge(priceMap, childHalfPriceMap, newPriceMap)
                    merge(halfPriceMap, childIgnoreMap, newHalfPriceMap)
                    merge(halfPriceMap, childHalfPriceMap, newHalfPriceMap)

                    ignoreMap = newIgnoreMap
                    priceMap = newPriceMap
                    halfPriceMap = newHalfPriceMap
                }

                return arrayOf(ignoreMap, priceMap, halfPriceMap)
            }

            val (ignoreMap, priceMap) = dfs(0)

            return maxOf(ignoreMap.values.maxOrNull() ?: 0, priceMap.values.maxOrNull() ?: 0)
        }
    }

    expect {
        Solution().maxProfit(
            3, intArrayOf(29, 45, 14), intArrayOf(1, 11, 9), arrayOf(intArrayOf(1, 2), intArrayOf(1, 3)), 136
        )
    }
}
