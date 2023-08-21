package p04xx

import util.expect
import kotlin.random.Random

fun main() {
    open class SolBase {
        fun rand7(): Int {
            return Random.nextInt(7) + 1
        }
    }

    class Solution : SolBase() {
        fun roll(): Int {
            return (rand7() - 1) * 7 + (rand7() - 1)
        }

        fun rand10(): Int {
            var t = roll()

            while (t >= 40) {
                t = roll()
            }
            return t / 4 + 1
        }
    }

    expect {
    }
}