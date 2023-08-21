package p19xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun recoverArray(n: Int, sums: IntArray): IntArray {
            fun dfs(size: Int, map: TreeMap<Int, Int>, route: List<Int>): IntArray? {
                return when {
                    0 !in map -> {
                        null
                    }

                    size == 0 -> {
                        route.toIntArray()
                    }

                    map.size == 1 -> {
                        dfs(size - 1, map, route + 0)
                    }

                    map.lastEntry().value % 2 == 0 && map.firstEntry().value % 2 == 0 -> {
                        val newMap = TreeMap<Int, Int>()
                        map.forEach { (key, value) ->
                            newMap[key] = value / 2
                        }

                        dfs(size - 1, newMap, route + 0)
                    }

                    else -> {
                        val max = map.lastKey()
                        val second = map.lowerKey(max)

                        val delta = max - second

                        val flag1 = delta in map
                        val flag2 = -delta in map

                        val newMap1 = TreeMap<Int, Int>()
                        val newMap2 = TreeMap<Int, Int>()

                        while (map.isNotEmpty()) {
                            val (num, count) = map.pollLastEntry()

                            map[num - delta]?.also {
                                if (it == count) {
                                    map.remove(num - delta)
                                } else {
                                    map[num - delta] = it - count
                                }
                            }
                            newMap1[num - delta] = count
                            newMap2[num] = count
                        }

                        flag1.takeIf { it }?.let {
                            dfs(size - 1, newMap1, route + delta)
                        } ?: flag2.takeIf { it }?.let {
                            dfs(size - 1, newMap2, route + (-delta))
                        }
                    }
                }
            }

            val map = TreeMap<Int, Int>()

            sums.forEach {
                map[it] = (map[it] ?: 0) + 1
            }

            return dfs(n, map, emptyList()) ?: intArrayOf()
        }
    }

    expect {
        Solution().recoverArray(
            3, intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3)
        ).toList()
    }
}