package p36xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countTrapezoids(points: Array<IntArray>): Int {
            val inf = 1e9 + 7
            val slopeToIntercept = hashMapOf<Double, MutableList<Double>>()
            val midToSlope = hashMapOf<Int, MutableList<Double>>()
            var result = 0

            for (i in 0 until points.size) {
                val x1 = points[i][0]
                val y1 = points[i][1]
                for (j in i + 1 until points.size) {
                    val x2 = points[j][0]
                    val y2 = points[j][1]
                    val dx = x1 - x2
                    val dy = y1 - y2
                    var k: Double
                    var b: Double

                    when (x2) {
                        x1 -> {
                            k = inf
                            b = x1.toDouble()
                        }

                        else -> {
                            k = 1.0 * (y2 - y1) / (x2 - x1)
                            b = 1.0 * (y1 * dx - x1 * dy) / dx
                        }
                    }
                    if (k == -0.0) {
                        k = 0.0
                    }
                    if (b == -0.0) {
                        b = 0.0
                    }
                    val mid = (x1 + x2) * 10000 + (y1 + y2)
                    slopeToIntercept.computeIfAbsent(k) { key: Double -> ArrayList<Double>() }.add(b)
                    midToSlope.computeIfAbsent(mid) { key: Int -> ArrayList<Double>() }.add(k)
                }
            }

            for (sti in slopeToIntercept.values) {
                if (sti.size == 1) {
                    continue
                }
                val cnt: MutableMap<Double, Int> = TreeMap<Double, Int>()
                for (b in sti) {
                    cnt[b] = cnt.getOrDefault(b, 0) + 1
                }
                var sum = 0
                for (count in cnt.values) {
                    result += sum * count
                    sum += count
                }
            }

            for (mts in midToSlope.values) {
                if (mts.size == 1) {
                    continue
                }
                val cnt: MutableMap<Double, Int> = TreeMap<Double, Int>()
                for (k in mts) {
                    cnt[k] = cnt.getOrDefault(k, 0) + 1
                }
                var sum = 0
                for (count in cnt.values) {
                    result -= sum * count
                    sum += count
                }
            }

            return result
        }
    }

    expect {
        Solution().countTrapezoids(
            arrayOf()
        )
    }
}
