package p19xx

import util.expect

fun main() {
    class Solution {
        fun numberOfCombinations(num: String): Int {
            val m = 1000000007
            val cache = Array(num.length) {
                IntArray(num.length + 1) {
                    -1
                }
            }

            fun dfs(index: Int, preLength: Int): Int {
                return when {
                    index > num.length -> {
                        0
                    }

                    index !in num.indices -> {
                        1
                    }

                    num[index] == '0' -> {
                        0
                    }

                    cache[index][preLength] >= 0 -> {
                        cache[index][preLength]
                    }

                    else -> {
                        var result = 0L
                        if (preLength > 0) {
                            var pre = index - preLength
                            var cur = index

                            loop@ while (pre < index) {
                                val curCh = num.getOrNull(cur) ?: break
                                when {
                                    curCh > num[pre] -> {
                                        pre = index
                                        break@loop
                                    }

                                    curCh < num[pre] -> {
                                        break@loop
                                    }

                                    else -> {
                                        pre++
                                        cur++
                                    }
                                }
                            }

                            if (pre == index) {
                                result += dfs(index + preLength, preLength)
                                result %= m
                            }
                        }

                        for (l in preLength + 1..num.length - index) {
                            result += dfs(index + l, l)
                            result %= m
                        }

                        cache[index][preLength] = result.toInt()
                        result.toInt()
                    }
                }
            }

            return dfs(0, 0)
        }
    }

    expect {
        Solution().numberOfCombinations(
            "1111"
        )
    }
}