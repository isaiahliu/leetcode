package p22xx

import util.expect

fun main() {
    class ATM {
        val cash = intArrayOf(20, 50, 100, 200, 500)
        val count = longArrayOf(0, 0, 0, 0, 0)

        fun deposit(banknotesCount: IntArray) {
            banknotesCount.forEachIndexed { index, i ->
                count[index] += i.toLong()
            }
        }

        fun withdraw(amount: Int): IntArray {
            val result = IntArray(5)

            var remain = amount.toLong()

            for (i in cash.lastIndex downTo 0) {
                val useCount = (remain / cash[i]).coerceAtMost(count[i])

                result[i] = useCount.toInt()
                remain -= useCount * cash[i]
            }

            return remain.takeIf { it == 0L }?.let {
                result.forEachIndexed { index, i ->
                    count[index] -= i.toLong()
                }

                result
            } ?: intArrayOf(-1)
        }
    }

    val atm = ATM()
    expect {
        atm.deposit(intArrayOf(0, 0, 1, 2, 1))
    }
    expect {
        atm.withdraw(600).toList()
    }

    expect {
        atm.deposit(intArrayOf(0, 1, 0, 1, 1))
    }
    expect {
        atm.withdraw(600).toList()
    }
    expect {
        atm.withdraw(550).toList()
    }
}