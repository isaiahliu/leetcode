package p16xx

import util.expect

fun main() {
    class Solution {
        fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            var result = 0

            repeat(1 shl requests.size) {
                val counts = IntArray(n)

                var balance = 0
                var people = 0
                it.forEachBit {
                    people++

                    requests[it].also { (from, to) ->
                        counts[from]++
                        if (counts[from] == 1) {
                            balance++
                        }
                        counts[to]--
                        if (counts[to] == 0) {
                            balance--
                        }
                    }
                }

                if (balance == 0) {
                    result = result.coerceAtLeast(people)
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumRequests(
            1, arrayOf()
        )
    }
}

