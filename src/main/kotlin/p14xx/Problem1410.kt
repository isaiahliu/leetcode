package p14xx

import util.expect

fun main() {
    class Solution {
        fun entityParser(text: String): String {
            return text.replace("&quot;", "\"")
                .replace("&apos;", "'")
                .replace("&gt;", ">")
                .replace("&lt;", "<")
                .replace("&frasl;", "/")
                .replace("&amp;", "&")
        }
    }

    expect {
        Solution().entityParser(
            ""
        )
    }
}

