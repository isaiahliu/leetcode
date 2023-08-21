package p08xx

import util.expect

fun main() {
    class Solution {
        fun hitBricks(grid: Array<IntArray>, hits: Array<IntArray>): IntArray {
            class Group {
                var size = 0

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            val root = Group()
            root.size = 0

            hits.forEach { (r, c) ->
                if (grid[r][c] > 0) {
                    grid[r][c]++
                }
            }

            val groups = hashMapOf<Pair<Int, Int>, Group>()

            fun addGroup(pos: Pair<Int, Int>): Group {
                val group = Group().also { it.size++ }
                groups[pos] = group
                if (pos.first == 0) {
                    group.join(root)
                } else {
                    groups[pos] = group

                    groups[pos.first - 1 to pos.second]?.join(group)
                    groups[pos.first to pos.second - 1]?.join(group)
                }

                return group
            }

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num == 1) {
                        addGroup(r to c)
                    }
                }
            }

            val result = IntArray(hits.size)
            for (index in hits.lastIndex downTo 0) {
                val node = hits[index].let { it[0] to it[1] }

                if (grid[node.first][node.second] == 0 || --grid[node.first][node.second] > 1) {
                    continue
                }

                val before = root.parent.size

                val group = addGroup(node)
                arrayOf(
                    node.first - 1 to node.second,
                    node.first + 1 to node.second,
                    node.first to node.second - 1,
                    node.first to node.second + 1
                ).mapNotNull {
                    groups[it]
                }.forEach {
                    it.join(group)
                }

                val after = root.parent.size

                result[index] = (after - before - 1).coerceAtLeast(0)
            }

            return result
        }

        fun hitBricks2(grid: Array<IntArray>, hits: Array<IntArray>): IntArray {
            val tasks = hashSetOf<Pair<Int, Int>>()
            grid[0].forEachIndexed { index, i ->
                if (i > 0) {
                    tasks.add(0 to index)
                }
            }

            var level = 1
            while (tasks.isNotEmpty()) {
                level++
                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        it.first != 0 && (grid.getOrNull(it.first)?.getOrNull(it.second) ?: 0) > 0
                    }.forEach {
                        if (grid[it.first][it.second] == 1) {
                            grid[it.first][it.second] = level
                            tasks.add(it)
                        }
                    }
                }
            }

            val visited = hashSetOf<Pair<Int, Int>>()
            val edges = hashSetOf<Pair<Int, Int>>()
            fun Pair<Int, Int>.bfs() {
                val currentLevel = grid[first][second]

                grid[first][second] = Int.MAX_VALUE

                arrayOf(first - 1 to second, first + 1 to second, first to second - 1, first to second + 1).filter {
                    grid.getOrNull(it.first)?.getOrNull(it.second) != null
                }.forEach {
                    val l = grid[it.first][it.second]
                    when {
                        l == currentLevel + 1 && visited.add(it) -> {
                            it.bfs()
                        }

                        l == 0 || l == Int.MAX_VALUE -> {}

                        else -> {
                            edges.add(it)
                        }
                    }
                }
            }

            return hits.map { (r, c) ->
                val removeLevel = grid[r][c]

                if (removeLevel == 0) {
                    return@map 0
                }

                grid[r][c] = 0

                var dropCount = 0

                arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach {
                    if (grid.getOrNull(it.first)?.getOrNull(it.second) == removeLevel + 1) {
                        visited.clear()
                        visited.add(it)

                        it.bfs()

                        edges.removeAll(visited)

                        if (edges.isEmpty()) {
                            dropCount += visited.size

                            visited.forEach { grid[it.first][it.second] = 0 }
                        } else {
                            while (edges.isNotEmpty()) {
                                edges.toSet().also { edges.clear() }.forEach { (r, c) ->
                                    val currentLevel = grid[r][c]
                                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                                        grid.getOrNull(it.first)?.getOrNull(it.second) != null
                                    }.forEach {
                                        if (grid[it.first][it.second] > currentLevel + 1) {
                                            grid[it.first][it.second] = currentLevel + 1
                                            edges.add(it)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                dropCount
            }.toIntArray()
        }
    }

    expect {
        Solution().hitBricks(
            arrayOf(
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1),
            ), arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 2),
                intArrayOf(1, 1),
            )
        ).toList()
    }
}