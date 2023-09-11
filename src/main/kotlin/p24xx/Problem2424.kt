package p24xx

import util.expect
import java.util.*

fun main() {
    class LUPrefix(n: Int) {
        val all = TreeSet<Int>()

        init {
            all.addAll(1..n + 1)
        }

        fun upload(video: Int) {
            all.remove(video)
        }

        fun longest(): Int {
            return all.first() - 1
        }
    }

    expect {
        LUPrefix(5).upload(
            5
        )
    }
}