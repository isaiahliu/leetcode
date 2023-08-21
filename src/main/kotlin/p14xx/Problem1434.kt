package p14xx

import util.expect

fun main() {
    class Solution {
        fun numberWays(hats: List<List<Int>>): Int {
            val m = 1000000007

            val liked = Array(41) { hashSetOf<Int>() }
            hats.forEachIndexed { index, l ->
                l.forEach {
                    liked[it].add(index)
                }
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(hatIndex: Int, status: Int): Int {
                val remainingPerson = hats.size - Integer.bitCount(status)
                if (remainingPerson == 0) {
                    return 1
                }

                if (remainingPerson > 41 - hatIndex) {
                    return 0
                }

                val cacheKey = hatIndex to status
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = dfs(hatIndex + 1, status) % m

                liked[hatIndex].forEach { person ->
                    val p = 1 shl person
                    if (status and p == 0) {
                        result += dfs(hatIndex + 1, status + p)

                        result %= m
                    }
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(1, 0)
        }
    }

    expect {
        Solution().numberWays(
            listOf(
                listOf(1, 2, 3, 4, 5, 12, 13, 16, 17, 18, 23, 24, 25, 26, 28, 30, 31, 33, 34, 38, 39, 40),
                listOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    8,
                    9,
                    10,
                    11,
                    12,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
                    20,
                    22,
                    23,
                    25,
                    26,
                    27,
                    28,
                    29,
                    31,
                    32,
                    33,
                    34,
                    35,
                    36,
                    37,
                    38,
                    39,
                    40
                ),
                listOf(1, 2, 3, 5, 6, 8, 9, 11, 12, 13, 17, 21, 22, 23, 27, 28, 29, 32, 33, 36, 37, 38, 39, 40),
                listOf(5, 10, 14, 15, 23, 24, 26, 29, 31, 33, 39, 40),
                listOf(
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    11,
                    12,
                    13,
                    14,
                    16,
                    17,
                    18,
                    19,
                    20,
                    21,
                    22,
                    23,
                    24,
                    25,
                    26,
                    27,
                    28,
                    30,
                    32,
                    33,
                    34,
                    35,
                    36,
                    39,
                    40
                ),
                listOf(1, 2, 3, 4, 6, 7, 11, 13, 15, 16, 18, 22, 23, 24, 25, 27, 29, 30, 34, 37, 38, 39, 40),
                listOf(1, 7, 11, 23, 24, 25, 28, 31),
                listOf(1, 3, 4, 5, 6, 7, 8, 9, 12, 17, 22, 23, 25, 30, 34, 35, 37, 38, 40),
                listOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
                    20,
                    21,
                    22,
                    23,
                    24,
                    25,
                    26,
                    27,
                    28,
                    30,
                    32,
                    36,
                    37,
                    38,
                    39
                ),
                listOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    12,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
                    20,
                    21,
                    22,
                    23,
                    24,
                    25,
                    26,
                    27,
                    28,
                    29,
                    30,
                    31,
                    32,
                    33,
                    34,
                    35,
                    37,
                    38,
                    39,
                    40
                )
            )
        )

        Solution().numberWays(
            listOf(
                listOf(3, 5, 1),
                listOf(3, 5),
            )
        )

        Solution().numberWays(
            listOf(
                listOf(1, 4, 5),
                listOf(1, 3, 4),
                listOf(1, 3),
                listOf(1, 3, 4, 5),
            )
        )
    }
}

