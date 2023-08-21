package p07xx

import util.expect

fun main() {
    class Solution {
        fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
            val emails = hashMapOf<String, MutableSet<Int>>()

            accounts.forEachIndexed { index, e ->
                e.drop(1).forEach {
                    emails.computeIfAbsent(it) { hashSetOf() }.add(index)
                }
            }

            val accountMap = accounts.mapIndexed { index, list -> index to list.drop(1) }.toMap().toMutableMap()

            val result = arrayListOf<List<String>>()

            while (emails.isNotEmpty()) {
                val first = emails.entries.first()
                emails.remove(first.key)

                val task = hashSetOf<Int>()
                task.addAll(first.value)
                val ids = hashSetOf<Int>()
                ids.addAll(task)

                while (task.isNotEmpty()) {
                    task.toSet().also { task.clear() }.mapNotNull { accountMap[it] }.forEach {
                        it.mapNotNull { email ->
                            emails[email].also { emails.remove(email) }
                        }.forEach {
                            it.forEach {
                                if (ids.add(it)) {
                                    task.add(it)
                                }
                            }
                        }
                    }
                }

                val t = arrayListOf(accounts[ids.first()][0])
                t.addAll(ids.mapNotNull { accountMap[it] }.flatten().toSet().sorted())

                result.add(t)
            }

            return result
        }
    }

    expect {
        Solution().accountsMerge(
            listOf()
        )
    }
}