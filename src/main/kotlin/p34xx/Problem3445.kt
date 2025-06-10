package p34xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxDifference(s: String, k: Int): Int {
            val chars = s.toSet()

            val counts = Array(s.length) {
                IntArray(5)
            }

            s.forEachIndexed { index, ch ->
                counts[index][ch - '0']++
            }

            var result = Int.MIN_VALUE
            ('0' until '5').filter { it in chars }.forEach { odd ->
                ('0' until '5').filter { it != odd && it in chars }.forEach { even ->
                    var diff = 0

                    val map = TreeMap<Int, MutableSet<Int>>()

                    val buff = LinkedList<Int>()

                    buff += 0

                    val sums = Array(2) { IntArray(s.length) }

                    s.forEachIndexed { index, ch ->
                        if (buff.size >= k) {
                            map.computeIfAbsent(buff.pollFirst()) { hashSetOf() } += index - k + 1
                        }

                        sums.forEach {
                            it[index] = it.getOrElse(index - 1) { 0 }
                        }
                        when (ch) {
                            odd -> {
                                diff++
                                sums[0][index]++
                            }

                            even -> {
                                diff--
                                sums[1][index]++
                            }

                            else -> {}
                        }

                        //check
                        loop@ for ((d, indices) in map) {
                            val r = diff - d

                            if (r <= result) {
                                break
                            }
                            for (left in indices) {
                                val oddCount = sums[0][index] - sums[0].getOrElse(left - 1) { 0 }
                                val evenCount = sums[1][index] - sums[1].getOrElse(left - 1) { 0 }

                                if (oddCount % 2 == 1 && evenCount > 0 && evenCount % 2 == 0) {
                                    result = r
                                    break@loop
                                }
                            }
                        }

                        buff += diff
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxDifference(
            "2421", 1
        )
    }
}
