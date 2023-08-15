package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxHappyGroups(batchSize: Int, groups: IntArray): Int {
            var fixed = 0

            val pos = LongArray(batchSize - 1) {
                1L shl it * 5
            }

            val masks = LongArray(batchSize - 1) {
                0b11111L shl it * 5
            }

            var init = 0L
            groups.forEach {
                (it % batchSize).also {
                    if (it == 0) {
                        fixed++
                    } else {
                        init += pos[it - 1]
                    }
                }
            }

            val cache = hashMapOf(0L to IntArray(batchSize))
            fun dfs(status: Long): IntArray {
                if (status in cache) {
                    return cache[status] ?: intArrayOf()
                }

                val result = IntArray(batchSize)

                pos.forEachIndexed { index, p ->
                    if (status and masks[index] > 0) {
                        dfs(status - p).forEachIndexed { rem, count ->
                            var add = 0
                            if (rem == 0) {
                                add++
                            }

                            ((rem + index + 1) % batchSize).also {
                                result[it] = result[it].coerceAtLeast(count + add)
                            }
                        }
                    }
                }

                cache[status] = result
                return result
            }
            return fixed + dfs(init).max()
        }
    }

    measureTimeMillis {
        Solution().maxHappyGroups(
            9, intArrayOf(6, 1, 1, 1, 6, 1, 1, 1, 7, 1, 1)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
