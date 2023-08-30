package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countPairs(nums: IntArray, k: Int): Long {
            val kFactors = hashMapOf<Int, Int>()

            var t = k
            var factor = 2
            while (factor * factor <= t) {
                var count = 0
                while (t % factor == 0) {
                    t /= factor
                    count++
                }

                if (count > 0) {
                    kFactors[factor] = count
                }

                factor++
            }

            if (t > 1) {
                kFactors[t] = 1
            }

            val factors = kFactors.keys.toIntArray()

            class Trie {
                val map = TreeMap<Int, Trie>()

                var size = 0L

                fun query(f: IntArray, index: Int): Long {
                    return if (index == factors.size) {
                        size
                    } else {
                        val count = f[index]
                        val targetCount = kFactors[factors[index]] ?: 0

                        return map.tailMap(targetCount - count, true).values.sumOf {
                            it.query(f, index + 1)
                        }
                    }
                }

                fun add(f: IntArray, index: Int) {
                    if (index == f.size) {
                        size++
                    } else {
                        map.computeIfAbsent(f[index]) { Trie() }.add(f, index + 1)
                    }
                }
            }

            val root = Trie()

            fun Int.countFactors(): IntArray {
                return IntArray(factors.size) {
                    var count = 0
                    var t = this
                    while (t % factors[it] == 0) {
                        t /= factors[it]
                        count++
                    }

                    count
                }
            }

            var result = 0L

            nums.forEach {
                val f = it.countFactors()
                result += root.query(f, 0)
                root.add(f, 0)
            }

            return result
        }
    }

    expect {
        Solution().countPairs(
            intArrayOf(), 1
        )
    }
}