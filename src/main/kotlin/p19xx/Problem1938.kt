package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxGeneticDifference(parents: IntArray, queries: Array<IntArray>): IntArray {
            val queryMap = hashMapOf<Int, MutableSet<Pair<Int, Int>>>()

            queries.forEachIndexed { index, (node, value) ->
                queryMap.computeIfAbsent(node) { hashSetOf() }.add(value to index)
            }

            class Trie(val bitPos: Int) {
                var size = 0

                val children = arrayOfNulls<Trie>(2)

                fun add(num: Int) {
                    size++

                    if (bitPos >= 0) {
                        val index = if (num and (1 shl bitPos) > 0) 1 else 0

                        if (children[index] == null) {
                            children[index] = Trie(bitPos - 1)
                        }

                        children[index]?.add(num)
                    }
                }

                fun remove(num: Int) {
                    size--
                    if (bitPos >= 0) {
                        val index = if (num and (1 shl bitPos) > 0) 1 else 0
                        children[index]?.remove(num)
                        if (children[index]?.size == 0) {
                            children[index] = null
                        }
                    }

                }

                fun query(num: Int, result: Int = 0): Int {
                    return when {
                        bitPos >= 0 -> {
                            val index = if (num and (1 shl bitPos) > 0) 0 else 1

                            children[index]?.query(num, result * 2 + index) ?: children[1 - index]?.query(
                                num, result * 2 + 1 - index
                            ) ?: 0
                        }

                        else -> result
                    }
                }
            }

            val root = Trie(17)

            val children = hashMapOf<Int, MutableSet<Int>>()

            var rootIndex = -1
            parents.forEachIndexed { nodeIndex, parentIndex ->
                if (parentIndex < 0) {
                    rootIndex = nodeIndex
                } else {
                    children.computeIfAbsent(parentIndex) { hashSetOf() }.add(nodeIndex)
                }
            }

            val result = IntArray(queries.size)
            fun Int.dfs() {
                root.add(this)

                queryMap[this]?.forEach { (value, queryIndex) ->
                    result[queryIndex] = value xor root.query(value)
                }

                children[this]?.forEach {
                    it.dfs()
                }

                root.remove(this)
            }

            rootIndex.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().maxGeneticDifference(
            intArrayOf(-1, 0, 1, 1), arrayOf(
                intArrayOf(0, 2)
            )
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}