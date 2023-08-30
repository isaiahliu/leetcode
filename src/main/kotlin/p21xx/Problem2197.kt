package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun replaceNonCoprimes(nums: IntArray): List<Int> {
            fun gcd(a: Int, b: Int): Int {
                return when {
                    b > a -> gcd(b, a)
                    a % b == 0 -> b
                    else -> gcd(b, a % b)
                }
            }

            val result = LinkedList<Int>()

            val queue = LinkedList<Int>()
            nums.forEach {
                queue.add(it)
            }

            while (queue.isNotEmpty()) {
                val next = queue.poll()

                when {
                    result.isEmpty() || gcd(result.peekLast(), next) == 1 -> result.add(next)
                    else -> {
                        val last = result.pollLast()
                        val gcd = gcd(last, next)

                        queue.push((next.toLong() * last / gcd).toInt())
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().replaceNonCoprimes(
            intArrayOf()
        )
    }
}