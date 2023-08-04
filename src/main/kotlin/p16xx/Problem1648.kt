package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfit(inventory: IntArray, orders: Int): Int {
            val m = 1000000007

            val map = TreeMap<Int, Int>()
            map[0] = 0

            inventory.forEach {
                map[it] = (map[it] ?: 0) + 1
            }

            var remain = orders
            var result = 0L

            while (remain > 0) {
                var (count, type) = map.pollLastEntry()

                val nextCount = map.lastKey()

                if (remain > type) {
                    (remain / type).coerceAtMost(count - nextCount).also {
                        result += (count.toLong() * 2 - it + 1) * it / 2 * type
                        result %= m
                        remain -= it * type
                        count -= it
                    }
                }

                if (remain <= type) {
                    result += count.toLong() * remain
                    result %= m
                    break
                }

                map[count] = (map[count] ?: 0) + type
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().maxProfit(
            intArrayOf(
                565259708,
                715164401,
                716563713,
                958255469,
                844600740,
                823949511,
                180479359,
                287829385,
                164248818,
                73361150,
                230686692,
                322986846,
                598720034,
                338241127,
                748922260,
                181241085,
                833659853,
                509571179,
                250093451,
                690995620,
                703292727,
                595636202
            ), 650114768
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}