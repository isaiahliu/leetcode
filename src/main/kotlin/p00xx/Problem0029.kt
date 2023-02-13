package p00xx

fun main() {
    class Solution {
        val bit30 = 1 shl 30

        fun divide(dividend: Int, divisor: Int): Int {
            when {
                dividend == divisor -> {
                    return 1
                }

                divisor == Int.MIN_VALUE -> {
                    return 0
                }

                divisor == 1 -> {
                    return dividend
                }

                divisor == -1 -> {
                    return if (dividend == Int.MIN_VALUE) {
                        Int.MAX_VALUE
                    } else {
                        0 - dividend
                    }
                }

                dividend == 0 -> {
                    return 0
                }

                dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0 -> {
                    return 0 - divide(dividend, 0 - divisor)
                }

                else -> {
                    var shlCount = 0
                    var sub = 0
                    if (dividend > 0) {
                        if (dividend < divisor) {
                            return 0
                        }

                        var t = divisor
                        var lastT = t
                        while (true) {
                            if (t >= dividend) {
                                t = lastT
                                shlCount--
                                break
                            }

                            if (t and bit30 > 0) {
                                break
                            }

                            shlCount++
                            lastT = t
                            t = t shl 1
                        }

                        sub = dividend - t
                    } else {
                        if (dividend > divisor) {
                            return 0
                        }

                        var t = divisor
                        var lastT = t
                        while (true) {
                            if (t <= dividend) {
                                t = lastT
                                shlCount--
                                break
                            }

                            if (t and bit30 == 0) {
                                break
                            }

                            shlCount++
                            lastT = t
                            t = t shl 1
                        }

                        sub = dividend - t
                    }

                    return (1 shl shlCount) + divide(sub, divisor)
                }
            }

        }
    }

    println(Solution().divide(1, Int.MIN_VALUE))
}

