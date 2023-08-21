package p13xx

import util.expect

fun main() {
    class Solution {
        fun watchedVideosByFriends(
            watchedVideos: List<List<String>>, friends: Array<IntArray>, id: Int, level: Int
        ): List<String> {
            val visited = hashSetOf(id)

            val tasks = hashSetOf(id)

            var currentLevel = 0

            while (tasks.isNotEmpty() && currentLevel < level) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    friends[it].forEach {
                        if (visited.add(it)) {
                            tasks.add(it)
                        }
                    }
                }

                currentLevel++
            }

            return tasks.map { watchedVideos[it] }.flatten().groupingBy { it }.eachCount().entries.sortedWith(
                compareBy<Map.Entry<String, Int>> { it.value }.thenBy { it.key }).map { it.key }
        }
    }

    expect {
        Solution().watchedVideosByFriends(
            listOf(), arrayOf(), 1, 2
        )
    }
}

