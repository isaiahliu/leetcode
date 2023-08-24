package p20xx

import util.expect

fun main() {
    class Solution {
        fun possiblyEquals(s1: String, s2: String): Boolean {
            val letter = 'a'..'z'
            fun String.lengthCache(): Array<out Set<Int>> {
                val result = Array(length + 1) { mutableSetOf<Int>() }
                result[length].add(0)

                for (index in length - 1 downTo 0) {
                    val current = result[index]
                    when (val c = this[index]) {
                        in letter -> {
                            result[index + 1].forEach {
                                current.add(it + 1)
                            }
                        }

                        in '1'..'9' -> {
                            var num = c - '0'
                            var length = 1
                            do {
                                result[index + length].forEach {
                                    current.add(it + num)
                                }

                                this.getOrNull(index + length)?.takeIf { it !in letter }?.also {
                                    length++
                                    num *= 10
                                    num += it - '0'
                                } ?: break
                            } while (true)
                        }
                    }
                }

                return result
            }

            fun String.numsCache(): Array<out Set<Pair<Int, Int>>> {
                val result = Array(length + 1) { mutableSetOf<Pair<Int, Int>>() }

                forEachIndexed { index, c ->
                    val current = result[index]

                    if (c !in letter && c != '0') {
                        var num = this[index] - '0'
                        var length = 1
                        current.add(num to length)

                        while (true) {
                            val next =
                                this.getOrNull(index + length)?.takeIf { it !in letter }?.let { it - '0' } ?: break

                            num *= 10
                            num += next
                            length++
                            current.add(num to length)
                        }
                    } else {
                        current.add(1 to 1)
                    }
                }

                result[result.lastIndex].add(0 to 0)
                return result
            }

            val cache1 = s1.lengthCache()
            val cache2 = s2.lengthCache()

            val nums1 = s1.numsCache()
            val nums2 = s2.numsCache()

            val visited = hashSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
            fun dfs(index1: Int, length1: Int, index2: Int, length2: Int): Boolean {
                return when {
                    index1 == s1.length && index2 == s2.length -> {
                        length1 == length2
                    }

                    index1 == s1.length -> {
                        cache2[index2].any { it + length2 == length1 }
                    }

                    index2 == s2.length -> {
                        cache1[index1].any { it + length1 == length2 }
                    }

                    !visited.add((index1 to length1) to (index2 to length2)) -> {
                        false
                    }

                    length1 > length2 -> {
                        nums2[index2].any { (n, l) ->
                            if (cache2[index2 + l].any { remain2 ->
                                    length2 + n + remain2 - length1 in cache1[index1]
                                }) {
                                dfs(index1, length1, index2 + l, length2 + n)
                            } else {
                                false
                            }
                        }
                    }

                    length1 < length2 || s1[index1] !in letter || s2[index2] !in letter || s1[index1] == s2[index2] -> {
                        nums1[index1].any { (n, l) ->
                            if (cache1[index1 + l].any { remain1 ->
                                    length1 + n + remain1 - length2 in cache2[index2]
                                }) {
                                dfs(index1 + l, length1 + n, index2, length2)
                            } else {
                                false
                            }
                        }
                    }

                    else -> {
                        false
                    }
                }
            }

            return dfs(0, 0, 0, 0)
        }
    }

    expect(false) {
        Solution().possiblyEquals(
            "98u8v8v8v89u888u998v88u98v88u9v99u989v8u", "9v898u98v888v89v998u98v9v888u9v899v998u9"
        )
    }

    expect(true) {
        Solution().possiblyEquals(
            "internationalization", "i18n"
        )
    }

    expect(true) {
        Solution().possiblyEquals(
            "l123e", "44"
        )
    }
}