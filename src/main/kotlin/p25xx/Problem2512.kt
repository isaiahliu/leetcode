package p25xx

import util.expect

fun main() {
    class Solution {
        fun topStudents(
            positive_feedback: Array<String>,
            negative_feedback: Array<String>,
            report: Array<String>,
            student_id: IntArray,
            k: Int
        ): List<Int> {
            val positive = positive_feedback.toSet()
            val negative = negative_feedback.toSet()

            val scores = report.map {
                it.split(" ").sumOf {
                    when (it) {
                        in positive -> {
                            3
                        }

                        in negative -> {
                            -1
                        }

                        else -> {
                            0
                        }
                    }.toInt()
                }
            }

            return scores.indices.sortedWith(compareByDescending<Int> { scores[it] }.thenBy { student_id[it] }).map {
                student_id[it]
            }.take(k)
        }
    }

    expect {
        Solution().topStudents(
            arrayOf(), arrayOf(), arrayOf(), intArrayOf(), 1
        )
    }
}