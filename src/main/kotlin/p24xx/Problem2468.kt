package p24xx

import util.expect

fun main() {
    class Solution {
        fun splitMessage(message: String, limit: Int): Array<String> {

            var maxLength = 5
            var maxWidth = 0
            var processedLength = 0L
            var count = 9
            while (processedLength < message.length) {

                if (maxLength >= limit) {
                    break
                }

                processedLength -= count / 10
                processedLength += (count - count / 10) * (limit - maxLength)

                if (processedLength >= message.length) {
                    val result = arrayListOf<String>()

                    var extraLength = maxWidth
                    val str = StringBuilder()

                    var part = 9
                    message.forEach {
                        str.append(it)

                        if (str.length == limit - maxLength + extraLength) {
                            result.add(str.toString())

                            str.clear()

                            if (result.size == part) {
                                part = part * 10 + 9
                                extraLength--
                            }
                        }
                    }

                    if (str.isNotEmpty()) {
                        result.add(str.toString())
                    }

                    return result.mapIndexed { index, s -> "$s<${index + 1}/${result.size}>" }.toTypedArray()
                }
                maxWidth++
                maxLength += 2
                count = count * 10 + 9
            }

            return emptyArray()
        }
    }

    expect {
        Solution().splitMessage(
            "short message", 15
        )
    }
}