package p19xx

import util.expect

fun main() {
    class Solution {
        fun numberOfRounds(loginTime: String, logoutTime: String): Int {
            fun String.toMinutes(): Int {
                return this.take(2).toInt() * 60 + this.takeLast(2).toInt()
            }

            val login = loginTime.toMinutes()
            var logout = logoutTime.toMinutes()

            if (logout < login) {
                logout += 24 * 60
            }

            var loginQuarter = login / 15
            if (login % 15 > 0) {
                loginQuarter++
            }

            return (logout / 15 - loginQuarter).coerceAtLeast(0)
        }
    }

    expect {
        Solution().numberOfRounds(
            "", ""
        )
    }
}
