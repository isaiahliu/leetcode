package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canReach(arr: IntArray, start: Int): Boolean {
            val visited = hashSetOf<Int>()

            fun dfs(pos: Int): Boolean {
                return when {
                    pos !in arr.indices -> {
                        false
                    }

                    !visited.add(pos) -> {
                        false
                    }

                    arr[pos] == 0 -> {
                        true
                    }

                    else -> dfs(pos + arr[pos]) || dfs(pos - arr[pos])
                }
            }

            return dfs(start)
        }
    }

    measureTimeMillis {
        Solution().canReach(
            intArrayOf(), 1
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

