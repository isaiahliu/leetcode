fun main() {
    class Solution {
        fun myAtoi(s: String): Int {
            var negative = false
            var hasDigits = false
            return buildString {
                var index = 0
                var step = 0
                while (index < s.length) {
                    val char = s[index]
                    when (step) {
                        0 -> {
                            if (char == ' ') {
                                index++
                            } else {
                                step++
                            }
                        }

                        1 -> {
                            when (char) {
                                '+' -> {
                                    index++
                                    step++
                                }

                                '-' -> {
                                    negative = true
                                    append("-")
                                    index++
                                    step++
                                }

                                else -> {
                                    step++
                                }
                            }
                        }

                        2 -> {
                            if (char == '0') {
                                index++
                            } else {
                                step++
                            }
                        }

                        3 -> {
                            if (char in '0'..'9') {
                                hasDigits = true
                                append(char)
                                index++
                            } else {
                                index = s.length
                            }
                        }
                    }
                }
            }.toIntOrNull() ?: when {
                !hasDigits -> 0
                negative -> Int.MIN_VALUE
                else -> Int.MAX_VALUE
            }
        }
    }
    println(Solution().myAtoi("    1"))
}

