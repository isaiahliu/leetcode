package p06xx

import util.expect

fun main() {
    class Solution {
        fun shoppingOffers(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int {
            var result = Int.MAX_VALUE

            fun process(cost: Int, specialStartIndex: Int, need: List<Int>) {
                if (cost >= result) {
                    return
                }

                result = result.coerceAtMost(cost + need.mapIndexed { index, i -> price[index] * i }.sum())

                loop@ for (i in specialStartIndex until special.size) {
                    val s = special[i]

                    var noneNeg = false
                    val newNeed = need.mapIndexed { index, n ->
                        (n - s[index]).also {
                            noneNeg = noneNeg || it < 0
                        }
                    }

                    if (!noneNeg) {
                        process(cost + s[s.lastIndex], i, newNeed)
                    }
                }
            }

            process(0, 0, needs)

            return result
        }
    }

    expect {
        Solution().shoppingOffers(
            listOf(2, 5), listOf(listOf(3, 0, 5), listOf(1, 2, 10)), listOf(3, 2)
        )

    }
}