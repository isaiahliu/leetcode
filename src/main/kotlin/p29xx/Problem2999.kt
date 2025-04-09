package p29xx

import util.expect

fun main() {
    class Solution {
        fun numberOfPowerfulInt(start: Long, finish: Long, limit: Int, s: String): Long {
            val to = finish.toString()
            val from = start.toString().padStart(to.length, '0')

            val FREE = 0b00
            val LOW_LIMIT = 0b01
            val HIGH_LIMIT = 0b10
            val BOTH_LIMIT = 0b11

            val cache = Array(to.length) {
                LongArray(4) { -1 }
            }

            fun dfs(index: Int, status: Int): Long {
                return when {
                    index == to.length -> 1L

                    cache[index][status] >= 0 -> cache[index][status]

                    else -> {
                        var lowerLimit = 0
                        var higherLimit = limit

                        var result = 0L

                        if (status and LOW_LIMIT > 0) {
                            lowerLimit = maxOf(from[index] - '0', lowerLimit)
                        }

                        if (status and HIGH_LIMIT > 0) {
                            higherLimit = minOf(to[index] - '0', higherLimit)
                        }

                        s.getOrNull(index + s.length - to.length)?.also {
                            lowerLimit = maxOf(it - '0', lowerLimit)
                            higherLimit = minOf(it - '0', higherLimit)
                        }

                        if (lowerLimit <= higherLimit) {
                            if (status == BOTH_LIMIT && lowerLimit == higherLimit && lowerLimit == from[index] - '0' && higherLimit == to[index] - '0') {
                                result += dfs(index + 1, BOTH_LIMIT)
                                lowerLimit++
                                higherLimit--
                            } else {
                                if (status and LOW_LIMIT > 0 && lowerLimit == from[index] - '0') {
                                    result += dfs(index + 1, LOW_LIMIT)
                                    lowerLimit++
                                }

                                if (status and HIGH_LIMIT > 0 && higherLimit == to[index] - '0') {
                                    result += dfs(index + 1, HIGH_LIMIT)
                                    higherLimit--
                                }
                            }
                        }

                        if (lowerLimit <= higherLimit) {
                            result += (higherLimit - lowerLimit + 1) * dfs(index + 1, FREE)
                        }

                        cache[index][status] = result
                        result
                    }
                }

            }

            return dfs(0, BOTH_LIMIT)
        }
    }

    expect {
        Solution().numberOfPowerfulInt(
            1, 971, 9, "72"
        )
    }
}
