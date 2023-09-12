package p24xx

import util.expect

fun main() {
    class Solution {
        fun mostPopularCreator(creators: Array<String>, ids: Array<String>, views: IntArray): List<List<String>> {
            val groups = creators.indices.groupBy { creators[it] }.map { (key, value) ->
                key to (value.maxWith(compareBy<Int> { views[it] }.thenByDescending { ids[it] }) to value.fold(0L) { a, b -> a + views[b] })
            }.toMap()

            val popular = groups.values.maxOf { it.second }

            return groups.filter { it.value.second == popular }.map { (key, p) ->
                listOf(key, ids[p.first])
            }
        }
    }

    expect {
        Solution().mostPopularCreator(
            arrayOf(), arrayOf(), intArrayOf()
        )
    }
}