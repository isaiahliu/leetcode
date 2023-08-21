package p13xx

import util.expect

fun main() {
    class Solution {
        fun maxStudents(seats: Array<CharArray>): Int {
            val cache = hashMapOf<Pair<Pair<Int, Int>, Pair<Int, Boolean>>, Int>()
            fun dfs(shape: Int, left: Boolean, rowIndex: Int, columnIndex: Int): Int {
                val leftPos = 1 shl (columnIndex - 1)
                if (columnIndex == seats[0].size) {
                    var newShape = shape
                    if (columnIndex > 0) {
                        if (left) {
                            newShape = newShape or leftPos
                        } else if (newShape and leftPos > 0) {
                            newShape -= leftPos
                        }
                    }
                    return dfs(newShape, false, rowIndex + 1, 0)
                }
                if (rowIndex == seats.size) {
                    return 0
                }

                val cacheKey = (rowIndex to columnIndex) to (shape to left)
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var allow = !left && seats[rowIndex][columnIndex] == '.'
                if (columnIndex > 0 && shape and leftPos > 0) {
                    allow = false
                }

                if (shape and (1 shl (columnIndex + 1)) > 0) {
                    allow = false
                }

                var newShape = shape
                if (columnIndex > 0) {
                    if (left) {
                        newShape = newShape or leftPos
                    } else if (newShape and leftPos > 0) {
                        newShape -= leftPos
                    }
                }
                var result = dfs(newShape, false, rowIndex, columnIndex + 1)
                if (allow) {
                    result = result.coerceAtLeast(dfs(newShape, true, rowIndex, columnIndex + 1) + 1)
                }

                cache[cacheKey] = result

                return result
            }

            return dfs(0, false, 0, 0)
        }
    }

    expect {
        Solution().maxStudents(
            arrayOf(
                charArrayOf('.', '#', '#', '.'),
                charArrayOf('.', '.', '.', '#'),
                charArrayOf('.', '.', '.', '.'),
                charArrayOf('#', '.', '#', '#'),
            )
        )
    }
}

