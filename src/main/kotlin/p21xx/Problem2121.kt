package p21xx

import util.expect
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun getDistances(arr: IntArray): LongArray {
            val result = LongArray(arr.size)

            arrayOf(arr.indices, arr.indices.reversed()).forEach {
                val numIndices = hashMapOf<Int, LinkedList<Int>>()

                it.forEach {
                    numIndices.computeIfAbsent(arr[it]) { LinkedList() }.add(it)
                }

                numIndices.values.forEach {
                    var count = 1L
                    var prev = it.poll()
                    var sum = 0L
                    while (it.isNotEmpty()) {
                        val next = it.poll()

                        sum += count * (next - prev).absoluteValue
                        result[next] += sum

                        prev = next
                        count++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().getDistances(
            intArrayOf()
        ).toList()
    }
}