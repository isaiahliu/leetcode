package p11xx

import util.expect

fun main() {
    class Solution {
        fun smallestSufficientTeam(req_skills: Array<String>, people: List<List<String>>): IntArray {
            val map = req_skills.mapIndexed { index, s -> s to index }.toMap()

            val skilled = hashMapOf<String, MutableSet<Int>>()

            people.forEachIndexed { index, skills ->
                skills.forEach {
                    skilled.computeIfAbsent(it) { hashSetOf() }.add(index)
                }
            }

            val target = (1 shl req_skills.size) - 1

            var result = people.indices.toList()

            fun dfs(skillStatus: Int, skillIndex: Int, peopleList: List<Int>) {
                if (peopleList.size >= result.size) {
                    return
                }

                if (skillStatus == target) {
                    result = peopleList
                    return
                }

                val skill = 1 shl skillIndex
                if (skill and skillStatus > 0) {
                    dfs(skillStatus, skillIndex + 1, peopleList)
                }

                skilled[req_skills[skillIndex]]?.forEach {
                    val t = people[it].mapNotNull {
                        map[it]
                    }.map {
                        1 shl it
                    }.sum()

                    dfs(skillStatus or t, skillIndex + 1, peopleList + it)
                }
            }

            dfs(0, 0, emptyList())
            return result.toIntArray()
        }
    }

    expect {
        Solution().smallestSufficientTeam(
            arrayOf("a", "b", "c"), listOf(listOf("a"), listOf("b"), listOf("b", "c"))
        ).toList()

    }
}