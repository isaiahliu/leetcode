package p32xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun numberOfAlternatingGroups(colors: IntArray, queries: Array<IntArray>): List<Int> {
            val map = TreeMap<Int, Int>()

            val sizeCounts = hashMapOf<Int, Int>()
            var start: Int? = null
            colors.indices.forEach {
                if (colors[it] != colors[(it + 1) % colors.size]) {
                    (start ?: it).also { s ->
                        start = s
                        map[s] = it
                    }
                } else {
                    start = null
                }
            }
            map.forEach { (from, to) ->
                (to - from + 1).also {
                    sizeCounts[it] = (sizeCounts[it] ?: 0) + 1
                }
            }

            return buildList {
                queries.forEach {
                    when (it[0]) {
                        1 -> {
                            val (_, size) = it

                            var result = 0

                            sizeCounts.forEach { s, count ->
                                result += maxOf(s - size + 2, 0) * count
                            }

                            if (map.isNotEmpty()) {
                                val (from1, to1) = map.firstEntry()
                                val (from2, to2) = map.lastEntry()

                                if (from1 == 0 && to2 == colors.lastIndex) {
                                    result -= maxOf(to1 - from1 - size + 3, 0)
                                    result -= maxOf(to2 - from2 - size + 3, 0)
                                    result += maxOf(to1 + colors.size - from2 - size + 3, 0)
                                }
                            }
                            add(result)
                        }

                        else -> {
                            val (_, index, color) = it

                            if (colors[index] != color) {
                                colors[index] = color

                                arrayOf((index - 1).mod(colors.size), index).forEach { index ->
                                    if (colors[index] == colors[(index + 1) % colors.size]) {
                                        val (from, to) = map.floorEntry(index)
                                        (to - from + 1).also { key ->
                                            sizeCounts[key]?.also {
                                                if (it == 1) {
                                                    sizeCounts -= key
                                                } else {
                                                    sizeCounts[key] = it - 1
                                                }
                                            }
                                        }

                                        if (index > from) {
                                            map[from] = index - 1
                                            sizeCounts[index - from] = (sizeCounts[index - from] ?: 0) + 1
                                        } else {
                                            map -= from
                                        }

                                        if (index < to) {
                                            map[index + 1] = to
                                            sizeCounts[to - index] = (sizeCounts[to - index] ?: 0) + 1
                                        }
                                    } else {
                                        var start = index
                                        var end = index
                                        map.lowerEntry(index)?.takeIf { it.value + 1 == index }?.key?.also {
                                            map[it] = index
                                            start = it
                                            sizeCounts[index - it]?.also { count ->
                                                if (count == 1) {
                                                    sizeCounts -= index - it
                                                } else {
                                                    sizeCounts[index - it] = count - 1
                                                }
                                            }
                                        }

                                        map[index + 1]?.also {
                                            map -= index + 1
                                            end = it

                                            sizeCounts[it - index]?.also { count ->
                                                if (count == 1) {
                                                    sizeCounts -= it - index
                                                } else {
                                                    sizeCounts[it - index] = count - 1
                                                }
                                            }
                                        }

                                        map[start] = end

                                        sizeCounts[end - start + 1] = (sizeCounts[end - start + 1] ?: 0) + 1
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    expect {
        Solution().numberOfAlternatingGroups(
            intArrayOf(1, 1, 1, 0), arrayOf(
                intArrayOf(1, 3)
            )
        )
    }
}
