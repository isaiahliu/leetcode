package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maskPII(s: String): String {
            "(\\w+)@(\\w+\\.\\w+)".toRegex().matchEntire(s)?.also {
                val (name, domain) = it.groupValues.drop(1).map { it.lowercase(Locale.getDefault()) }

                return "${name[0]}*****${name[name.lastIndex]}@${domain}"
            }

            "(\\d{0,3})\\d{6}(\\d{4})".toRegex().matchEntire(s.replace("\\D".toRegex(), ""))?.also {
                val (area, last4) = it.groupValues.drop(1)

                return "${
                    area.takeIf { it.isNotEmpty() }?.let { "+${"*".repeat(it.length)}-" }.orEmpty()
                }***-***-${last4}"
            }

            return s
        }
    }

    expect {
        Solution().maskPII(
            "1(234)567-8901"
        )
    }
}