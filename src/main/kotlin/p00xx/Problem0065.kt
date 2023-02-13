package p00xx

fun main() {
    class Solution {
        fun isNumber(s: String): Boolean {
            //0 符号
            //1 整数位第一个数字
            //2 整数位其它数字
            //3 小数位第一个数字
            //4 小数位
            //5 e符号
            //6 e第一个数字
            //7 e其它数字
            var step = 0

            val sign = arrayOf('+', '-')
            val digit = '0'..'9'
            val dot = '.'
            val e = arrayOf('e', 'E')
            s.forEach {
                when (it) {
                    in sign -> {
                        when (step) {
                            0, 5 -> {
                                step++
                            }

                            else -> return false
                        }
                    }

                    in digit -> {
                        step = when (step) {
                            0, 1, 2 -> {
                                2
                            }

                            3, 4 -> {
                                4
                            }

                            5, 6, 7 -> {
                                7
                            }

                            else -> return false
                        }
                    }

                    dot -> {
                        step = when (step) {
                            0, 1 -> {
                                3
                            }

                            2 -> {
                                4
                            }

                            else -> return false
                        }
                    }

                    in e -> {
                        step = when (step) {
                            2, 4 -> {
                                5
                            }

                            else -> return false
                        }
                    }

                    else -> return false
                }
            }

            return step in arrayOf(2, 4, 7)
        }
    }

    println(Solution().isNumber("2e0"))
}

