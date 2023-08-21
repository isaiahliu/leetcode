package p05xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isValid(code: String): Boolean {
            var inCData = false
            val tags = LinkedList<String>()
            var inStartTag = false
            var inCloseTag = false
            val tagContent = StringBuilder()

            var index = 0
            while (index < code.length) {
                val c = code[index]
                when {
                    inCData -> {
                        if (c == ']' && code.startsWith("]]>", index)) {
                            index += 3
                            inCData = false
                        } else {
                            index++
                        }
                    }

                    inStartTag -> {
                        when (c) {
                            '>' -> {
                                tags.push(tagContent.toString().takeIf { it.length in 1..9 } ?: return false)
                                inStartTag = false
                            }

                            in 'A'..'Z' -> {
                                tagContent.append(c)
                            }

                            else -> {
                                return false
                            }
                        }
                        index++
                    }

                    inCloseTag -> {
                        when (c) {
                            '>' -> {
                                tags.poll().takeIf { it == tagContent.toString().ifEmpty { return false } }
                                    ?: return false
                                inCloseTag = false
                            }

                            in 'A'..'Z' -> {
                                tagContent.append(c)
                            }

                            else -> {
                                return false
                            }

                        }
                        index++
                    }

                    c == '<' -> {
                        if (code.startsWith("/", index + 1)) {
                            inCloseTag = true
                            tagContent.clear()
                            index += 2
                        } else if (code.startsWith("![CDATA[", index + 1)) {
                            if (tags.isEmpty()) {
                                return false
                            }

                            inCData = true
                            index += 9
                        } else {
                            if (tags.isEmpty() && index > 0) {
                                return false
                            }

                            inStartTag = true
                            tagContent.clear()
                            index++
                        }
                    }

                    else -> {
                        if (tags.isEmpty()) {
                            return false
                        }

                        index++
                    }
                }
            }

            return !inStartTag && !inCloseTag && tags.isEmpty()
        }
    }

    expect {
        Solution().isValid("<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>")

    }
}