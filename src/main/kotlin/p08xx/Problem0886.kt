package p08xx

import util.expect

fun main() {
    class Solution {
        fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
            val dislikeMap = hashMapOf<Int, MutableSet<Int>>()

            dislikes.forEach { (a, b) ->
                dislikeMap.computeIfAbsent(a) { hashSetOf() }.add(b)
                dislikeMap.computeIfAbsent(b) { hashSetOf() }.add(a)
            }

            val groups = Array(2) { hashSetOf<Int>() }

            while (dislikeMap.isNotEmpty()) {
                val (first, dl) = dislikeMap.entries.first()
                groups[0].add(first)
                groups[1].addAll(dl)
                dislikeMap.remove(first)

                val tasks = dl.toMutableSet()

                var group = 0
                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach {
                        dislikeMap[it]?.forEach {
                            if (it in groups[1 - group]) {
                                return false
                            } else if (groups[group].add(it)) {
                                tasks.add(it)
                            }
                        }
                        dislikeMap.remove(it)
                    }

                    group = 1 - group
                }
            }

            return true
        }
    }

    expect {
        Solution().possibleBipartition(
            4, arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3),
            )
        )
    }
}