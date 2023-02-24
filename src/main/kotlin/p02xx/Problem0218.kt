package p02xx

import java.util.*

fun main() {
    class Solution {
        fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
            val lefts = LinkedList(buildings.map { it[0] to it[2] }.sortedBy { it.first })
            val rights = LinkedList(buildings.map { it[1] to it[2] }.sortedBy { it.first })

            val result = hashMapOf<Int, Int>()

            val heightStack = LinkedList<Int>()
            heightStack.push(0)

            while (lefts.isNotEmpty() || rights.isNotEmpty()) {
                if (lefts.isNotEmpty() && (rights.isEmpty() || lefts.peek().first <= rights.peek().first)) {
                    val (index, height) = lefts.pop()

                    if (height > heightStack.peek()) {
                        result[index] = height
                        heightStack.push(height)
                    } else {
                        heightStack.add(heightStack.indexOfFirst { it < height }, height)
                    }
                } else {
                    val (index, height) = rights.pop()

                    if (height == heightStack.peek()) {
                        heightStack.pop()

                        if (height > heightStack.peek()) {
                            result[index] = heightStack.peek()
                        }
                    } else {
                        heightStack.remove(height)
                    }
                }
            }

            return result.map { listOf(it.key, it.value) }.sortedBy { it[0] }
        }
    }

    println(
        Solution().getSkyline(
            arrayOf(
                intArrayOf(0, 2, 3),
                intArrayOf(2, 5, 3),
            )
        )
    )
}

