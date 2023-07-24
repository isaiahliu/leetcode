package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSizeSlices(slices: IntArray): Int {
            val size = slices.size

            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun dfs(from: Int, to: Int, total: Boolean = false): Int {
                if (from == to && !total) {
                    return 0
                }

                val cacheKey = from to to

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val rangeFrom = (from + 1) % size
                val rangeTo = (to + size - 1) % size
                var split = rangeFrom
                var result = slices[split] + dfs((split + 1) % size, rangeTo)

                split = (split + 3) % size
                while ((to + 1) % size != split) {
                    result =
                        result.coerceAtLeast(slices[split] + dfs(rangeFrom, split) + dfs((split + 1) % size, rangeTo))

                    split = (split + 3) % size
                }

                split = (from + 3) % size
                while (split != to) {
                    result = result.coerceAtLeast(dfs(from, split) + dfs(split, to))

                    split = (split + 3) % size
                }

                cache[cacheKey] = result

                return result
            }

            var result = 0
            slices.indices.forEach {
                result = result.coerceAtLeast(dfs(it, it, true))
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

