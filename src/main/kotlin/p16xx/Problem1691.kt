package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxHeight(cuboids: Array<IntArray>): Int {
            cuboids.forEach { it.sort() }
            val cubes = cuboids.groupingBy { it[0] to it[1] to it[2] }.eachCount().entries.toList()

            val cache = IntArray(cubes.size)
            fun dfs(index: Int): Int {
                if (cache[index] > 0) {
                    return cache[index]
                }

                val (cube, count) = cubes[index]
                val (b, height) = cube
                val (width, length) = b

                var result = 0

                cubes.forEachIndexed { cubeIndex, (cube2, _) ->
                    if (cubeIndex != index) {
                        val (b2, height2) = cube2
                        val (width2, length2) = b2

                        if (width2 <= width && length2 <= length && height2 <= height) {
                            result = result.coerceAtLeast(dfs(cubeIndex))
                        }
                    }
                }

                result += height * count

                cache[index] = result
                return result
            }

            var result = 0

            cubes.indices.forEach { c ->
                result = result.coerceAtLeast(dfs(c))
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxHeight(
            arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

