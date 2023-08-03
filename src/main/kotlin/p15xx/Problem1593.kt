package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxUniqueSplit(s: String): Int {
            var result = 1

            fun dfs(index: Int, route: Set<String>) {
                if (s.substring(index) !in route) {
                    result = result.coerceAtLeast(route.size + 1)
                }

                for (i in index until s.lastIndex) {
                    val newRoute = route + s.substring(index..i)
                    if (newRoute.size != route.size) {
                        dfs(i + 1, newRoute)
                    }
                }
            }

            dfs(0, emptySet())

            return result
        }
    }

    measureTimeMillis {
        Solution().maxUniqueSplit(
            "ababccc"
        ).also { println("${it} should be ${it}") }
    }
}

