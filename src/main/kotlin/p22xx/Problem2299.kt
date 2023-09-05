package p22xx

import util.expect

fun main() {
    class Solution {
        fun strongPasswordCheckerII(password: String): Boolean {
            if (password.length < 8) {
                return false
            }

            var lower = false
            var higher = false
            var number = false
            var special = false

            val lowers = 'a'..'z'
            val highers = 'A'..'Z'
            val numbers = '0'..'9'
            val specials = setOf('!', '@', '#', '\\', '$', '%', '^', '&', '*', '(', ')', '-', '+')

            var pre = password[0] + 1
            password.forEach {
                when (it) {
                    pre -> {
                        return false
                    }

                    in lowers -> {
                        lower = true
                    }

                    in highers -> {
                        higher = true
                    }

                    in numbers -> {
                        number = true
                    }

                    in specials -> {
                        special = true
                    }
                }

                pre = it
            }

            return lower and higher and number and special
        }
    }

    expect {
        Solution().strongPasswordCheckerII(
            ""
        )
    }
}