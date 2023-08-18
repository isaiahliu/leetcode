package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSizeSlices(slices: IntArray): Int {
            val size = slices.size

            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun dfs(from: Int, rangeSize: Int): Int {
                if (rangeSize == 0) {
                    return 0
                }

                if (rangeSize == 3) {
                    return slices[(from + 1) % size]
                }

                val cacheKey = from to rangeSize

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0
                var leftPart = 3
                while (leftPart < rangeSize) {
                    result =
                        result.coerceAtLeast(dfs(from, leftPart) + dfs((from + leftPart) % size, rangeSize - leftPart))
                    leftPart += 3
                }

                leftPart = 0
                while (leftPart < rangeSize) {
                    result = result.coerceAtLeast(
                        slices[(from + leftPart + 1) % size] + dfs(from + 1, leftPart) + dfs(
                            (from + leftPart + 2) % size, rangeSize - leftPart - 3
                        )
                    )

                    leftPart += 3
                }

                cache[cacheKey] = result

                return result
            }

            var result = 0
            slices.indices.forEach {
                result = result.coerceAtLeast(dfs(it, slices.size))
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxSizeSlices(
            intArrayOf(1, 2, 3, 4, 5, 6)
        ).also { println(it) }

        Solution().maxSizeSlices(
            intArrayOf(
                3,
                95,
                6,
                53,
                77,
                76,
                22,
                47,
                20,
                42,
                32,
                79,
                6,
                41,
                76,
                73,
                72,
                17,
                65,
                91,
                57,
                67,
                61,
                35,
                94,
                47,
                48,
                70,
                63,
                33,
                96,
                20,
                16,
                47,
                38,
                48,
                64,
                48,
                5,
                95,
                43,
                45,
                48,
                63,
                70,
                92,
                58,
                77,
                89,
                78,
                94,
                33,
                29,
                2,
                40,
                29,
                81,
                99,
                72,
                86,
                7,
                41,
                99,
                22,
                25,
                89,
                15,
                58,
                27,
                17,
                10,
                95,
                54,
                30,
                36,
                41,
                44,
                64,
                45,
                26,
                94,
                38,
                93,
                99,
                57,
                62,
                44,
                25,
                76,
                75,
                97,
                77,
                55,
                22,
                15,
                49
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

