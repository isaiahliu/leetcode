package p20xx

import util.expect

fun main() {
    class Bank(val balance: LongArray) {
        fun transfer(account1: Int, account2: Int, money: Long): Boolean {
            return when {
                account1 - 1 !in balance.indices -> false
                account2 - 1 !in balance.indices -> false
                balance[account1 - 1] < money -> false
                else -> {
                    balance[account1 - 1] -= money
                    balance[account2 - 1] += money
                    true
                }
            }
        }

        fun deposit(account: Int, money: Long): Boolean {
            return when {
                account - 1 !in balance.indices -> false
                else -> {
                    balance[account - 1] += money
                    true
                }
            }
        }

        fun withdraw(account: Int, money: Long): Boolean {
            return when {
                account - 1 !in balance.indices -> false
                balance[account - 1] < money -> false
                else -> {
                    balance[account - 1] -= money
                    true
                }
            }
        }
    }

    expect {
        Bank(longArrayOf()).transfer(
            1, 1, 1
        )
    }
}