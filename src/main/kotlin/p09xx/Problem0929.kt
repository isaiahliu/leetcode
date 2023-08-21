package p09xx

import util.expect

fun main() {
    class Solution {
        fun numUniqueEmails(emails: Array<String>): Int {
            fun String.clean(): String {
                val str = StringBuilder()

                var ignoring = false
                var domain = false
                forEach {
                    when {
                        it == '@' -> {
                            str.append(it)
                            domain = true
                        }

                        domain -> {
                            str.append(it)
                        }

                        ignoring -> {

                        }

                        it == '+' -> {
                            ignoring = true
                        }

                        it == '.' -> {

                        }

                        else -> {
                            str.append(it)
                        }
                    }
                }

                return str.toString()
            }

            return emails.map { it.clean() }.toSet().size
        }
    }

    expect {
        Solution().numUniqueEmails(
            arrayOf()
        )
    }
}