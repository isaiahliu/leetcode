package pinter17

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val format = DecimalFormat("#0.0000")

        fun intersectCount(array1: IntArray, array2: IntArray): Int {
            var index1 = 0
            var index2 = 0

            var count = 0

            while (index1 < array1.size && index2 < array2.size) {
                val num1 = array1[index1]
                val num2 = array2[index2]
                if (num1 == num2) {
                    index1++
                    index2++
                    count++
                } else if (num1 < num2) {
                    index1++
                } else {
                    index2++
                }
            }

            return count
        }

        fun computeSimilarities(docs: Array<IntArray>): List<String> {
            val result = arrayListOf<String>()

            docs.forEach { it.sort() }

            for (i in docs.indices) {
                for (j in i + 1 until docs.size) {
                    val docs1 = docs[i]
                    val docs2 = docs[j]

                    var union = (docs1 + docs2).toSet().size
                    val intersect = docs1.size + docs2.size - union
                    if (intersect > 0) {
                        val r = intersect.toBigDecimal()
                            .divide(union.toBigDecimal(), 4, RoundingMode.HALF_UP)

                        if (r > BigDecimal.ZERO) {
                            result += "${i},${j}: ${format.format(r)}"
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().intersectCount(intArrayOf(0, 0, 1, 2, 5), intArrayOf(0, 0, 1, 2, 5)).also { println(it) }
    }
}

