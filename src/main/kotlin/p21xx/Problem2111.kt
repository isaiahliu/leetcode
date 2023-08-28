package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun kIncreasing(arr: IntArray, k: Int): Int {
            var result = arr.size
            repeat(k) {
                val map = TreeMap<Int, Int>()
                map[0] = 0
                for (i in it until arr.size step k) {
                    val num = arr[i]

                    val length = map.floorEntry(num).value + 1

                    while (true) {
                        map.ceilingEntry(num)?.takeIf { it.value <= length }?.also {
                            map.remove(it.key)
                        } ?: break
                    }

                    map[num] = length
                }

                result -= map.lastEntry().value
            }

            return result
        }
    }

    expect {
        Solution().kIncreasing(
            intArrayOf(), 1
        )
    }
}