package p16xx

import util.expect

fun main() {
    class Solution {
        fun interpret(command: String): String {
            return command.replace("(al)", "al").replace("()", "o")
        }
    }

    expect {
        Solution().interpret(
            ""
        )
    }
}

