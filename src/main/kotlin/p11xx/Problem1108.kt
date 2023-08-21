package p11xx

import util.expect

fun main() {
    class Solution {
        fun defangIPaddr(address: String): String {
            return address.replace(".", "[.]")
        }
    }

    expect {
        Solution().defangIPaddr(
            "1.1.1.1"
        )

    }
}