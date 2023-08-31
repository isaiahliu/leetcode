package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun longestRepeating(s: String, queryCharacters: String, queryIndices: IntArray): IntArray {
            val segs = Array(26) {
                TreeMap<Int, Int>()
            }

            val chars = s.toCharArray()

            var pre = s[0]
            var count = 0
            val sums = TreeMap<Int, Int>()

            fun inc(length: Int) {
                sums[length] = (sums[length] ?: 0) + 1
            }

            fun dec(length: Int) {
                sums[length]?.also {
                    if (it == 1) {
                        sums.remove(length)
                    } else {
                        sums[length] = it - 1
                    }
                }
            }


            "$s ".forEachIndexed { index, c ->
                if (c == pre) {
                    count++
                } else {
                    segs[pre - 'a'][index - count] = index - 1
                    inc(count)

                    count = 1
                    pre = c
                }
            }

            fun TreeMap<Int, Int>.split(index: Int) {
                floorEntry(index)?.also { (from, to) ->
                    remove(from)
                    dec(to - from + 1)

                    if (from < to) {
                        when (index) {
                            from -> {
                                put(from + 1, to)
                                inc(to - from)
                            }

                            to -> {
                                put(from, to - 1)
                                inc(to - from)
                            }

                            else -> {
                                put(from, index - 1)
                                put(index + 1, to)
                                inc(index - from)
                                inc(to - index)
                            }
                        }
                    }
                }
            }

            fun TreeMap<Int, Int>.add(index: Int) {
                var start = index
                var end = index
                lowerEntry(index)?.takeIf { it.value == index - 1 }?.also { (from, to) ->
                    remove(from)
                    dec(to - from + 1)
                    start = from
                }

                this[index + 1]?.also { to ->
                    remove(index + 1)
                    dec(to - index)
                    end = to
                }

                put(start, end)
                inc(end - start + 1)
            }

            return queryIndices.mapIndexed { index, i ->
                if (chars[i] != queryCharacters[index]) {
                    segs[chars[i] - 'a'].split(i)

                    chars[i] = queryCharacters[index]

                    segs[chars[i] - 'a'].add(i)
                }

                sums.lastKey()
            }.toIntArray()
        }
    }

    expect {
        Solution().longestRepeating(
            "babacc", "bcb", intArrayOf(1, 3, 3)
        ).toList()
    }
}