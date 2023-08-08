package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reformatNumber(number: String): String {
            val phoneNo = LinkedList(number.replace(" ", "").replace("-", "").toList())

            val result = StringBuilder()

            while (phoneNo.size > 4) {
                repeat(3) {
                    result.append(phoneNo.poll())
                }
                result.append('-')
            }

            if (phoneNo.size == 4) {
                repeat(2) {
                    result.append(phoneNo.poll())
                }

                result.append('-')
            }

            while (true) {
                result.append(phoneNo.poll() ?: break)
            }

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().reformatNumber(
            ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
