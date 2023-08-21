package p17xx

import util.expect

fun main() {
    class Solution {
        fun maximizeXor(nums: IntArray, queries: Array<IntArray>): IntArray {
            val pos = IntArray(31) {
                1 shl (30 - it)
            }

            class Trie {
                val children = arrayOfNulls<Trie>(2)

                var value: Int? = null

                fun init(num: Int, posIndex: Int) {
                    pos.getOrNull(posIndex)?.let { num and it }?.let { if (it > 0) 1 else 0 }?.also {
                        (children[it] ?: Trie().also { item ->
                            children[it] = item
                        }).init(num, posIndex + 1)
                    } ?: run {
                        value = num
                    }
                }

                fun search(num: Int, posIndex: Int, max: Int, free: Boolean): Int? {
                    return value ?: run {
                        val numBit = if ((num and pos[posIndex]) > 0) 1 else 0
                        val expect = numBit xor 1

                        return if (free) {
                            children[expect]?.search(num, posIndex + 1, max, true) ?: children[expect xor 1]?.search(
                                num, posIndex + 1, max, true
                            )
                        } else {
                            val maxBit = if ((max and pos[posIndex]) > 0) 1 else 0

                            when {
                                maxBit == 1 && expect == 1 -> {
                                    children[1]?.search(num, posIndex + 1, max, false) ?: children[0]?.search(
                                        num, posIndex + 1, max, true
                                    )
                                }

                                maxBit == 1 -> {
                                    children[0]?.search(num, posIndex + 1, max, true) ?: children[1]?.search(
                                        num, posIndex + 1, max, false
                                    )
                                }

                                else -> {
                                    children[0]?.search(num, posIndex + 1, max, false)
                                }
                            }
                        }
                    }
                }
            }

            val root = Trie()

            nums.forEach {
                root.init(it, 0)
            }

            return queries.map { (query, max) ->
                root.search(query, 0, max, false)?.let { it xor query } ?: -1
            }.toIntArray()
        }
    }

    expect {
        Solution().maximizeXor(
            intArrayOf(5, 2, 4, 6, 6, 3), arrayOf(
                intArrayOf(12, 4),
                intArrayOf(8, 1),
                intArrayOf(6, 3),
            )
        ).toList()
    }
}
