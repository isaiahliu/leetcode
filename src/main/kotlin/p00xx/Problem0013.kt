package p00xx

fun main() {
    class Solution {
        val pairs = mapOf(
            'M' to (1 to 1000),
            'D' to (5 to 100),
            'C' to (1 to 100),
            'L' to (5 to 10),
            'X' to (1 to 10),
            'V' to (5 to 1),
            'I' to (1 to 1)
        )

        fun romanToInt(s: String): Int {
            var index = 0

            var result = 0
            while (index < s.length) {
                val (digit, tens) = pairs[s[index++]] ?: break

                //1, 5
                var num = digit * tens
                when (digit) {
                    1 -> {
                        var nextNum = s.getOrNull(index)?.let { pairs[it] }
                        if (nextNum != null) {
                            val (nextDigit, nextTens) = nextNum
                            when {
                                nextDigit == 1 && nextTens == tens -> {
                                    index++
                                    //2
                                    num += tens

                                    nextNum = s.getOrNull(index)?.let { pairs[it] }
                                    if (nextNum != null) {
                                        if (nextNum.first == 1 && nextNum.second == tens) {
                                            index++
                                            //3
                                            num += tens
                                        }
                                    }
                                }

                                nextDigit == 5 && nextTens == tens -> {
                                    index++
                                    //4
                                    num *= 4
                                }

                                nextDigit == 1 && nextTens == tens * 10 -> {
                                    index++
                                    //9
                                    num *= 9
                                }
                            }
                        }
                    }

                    5 -> {
                        var nextNum = s.getOrNull(index)?.let { pairs[it] }
                        if (nextNum != null) {
                            if (nextNum.first == 1 && nextNum.second == tens) {
                                index++
                                //6
                                num += tens

                                nextNum = s.getOrNull(index)?.let { pairs[it] }
                                if (nextNum != null) {
                                    if (nextNum.first == 1 && nextNum.second == tens) {
                                        index++
                                        //7
                                        num += tens

                                        nextNum = s.getOrNull(index)?.let { pairs[it] }
                                        if (nextNum != null) {
                                            if (nextNum.first == 1 && nextNum.second == tens) {
                                                index++
                                                //8
                                                num += tens
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                result += num
            }

            return result
        }
    }

    Solution().romanToInt("IX")
    Solution().romanToInt("LVIII")
    Solution().romanToInt("MCMXCIV")
}

