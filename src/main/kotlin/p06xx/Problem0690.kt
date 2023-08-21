package p06xx

import util.expect

fun main() {
    class Employee {
        var id: Int = 0
        var importance: Int = 0
        var subordinates: List<Int> = listOf()
    }

    class Solution {
        fun getImportance(employees: List<Employee?>, id: Int): Int {
            val cache = hashMapOf<Int, Int>()

            val empl = employees.filterNotNull().associateBy { it.id }

            fun Int.calculate(): Int {
                if (this in cache) {
                    return cache[this] ?: 0
                }

                val employee = empl[this] ?: return 0

                val result = employee.importance + employee.subordinates.map { it.calculate() }.sum()

                cache[this] = result

                return result
            }

            return id.calculate()
        }
    }

    expect {
        Solution().getImportance(
            listOf(), 1
        )
    }
}