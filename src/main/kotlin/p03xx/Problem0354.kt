package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxEnvelopes(envelopes: Array<IntArray>): Int {
            //比当前宽度大的结果中，不同高度的最大解，value应递减，若出现递增，说明更高的出现更优解，删除前面的值
            //1 -- 4
            //3 -- 2
            //5 -- 1
            //若插入3--4 删除 key 1, 3
            val map = TreeMap<Int, Int>()

            val widthGroup = envelopes.groupBy { it[0] }

            widthGroup.keys.sortedDescending().forEach {
                val temp = sortedMapOf<Int, Int>()
                widthGroup[it]?.map { it[1] }?.forEach { height ->
                    val count = (map.higherEntry(height)?.value ?: 0) + 1

                    if ((temp[height] ?: 0) < count) {
                        temp[height] = count
                    }
                }

                temp.forEach { (key, value) ->
                    if ((map[key] ?: 0) < value) {
                        while (true) {
                            map.lowerEntry(key)?.takeIf { it.value < value }?.also {
                                map.remove(it.key)
                            } ?: break
                        }

                        map[key] = value
                    }
                }
            }

            return map.firstEntry().value
        }
    }

    expect {
        Solution().maxEnvelopes(
            arrayOf(
                intArrayOf(5, 4),
                intArrayOf(6, 4),
                intArrayOf(6, 7),
                intArrayOf(2, 3)
            )
        )
    }
}

