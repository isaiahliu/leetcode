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

                        val newMap1 = if (delta in map) TreeMap<Int, Int>() else null
                        val newMap2 = if (-delta in map) TreeMap<Int, Int>() else null

                        while (map.isNotEmpty()) {
                            val (num, count) = map.pollLastEntry()

                            map[num - delta]?.also {
                                if (it == count) {
                                    map.remove(num - delta)
                                } else {
                                    map[num - delta] = it - count
                                }
                            }
                            newMap1?.put(num - delta, count)
                            newMap2?.put(num, count)
                        }

                        newMap1?.let {
                            dfs(size - 1, it, route + delta)
                        } ?: newMap2?.let {
                            dfs(size - 1, it, route + (-delta))
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