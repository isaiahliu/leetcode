package p09xx

import util.expect

fun main() {
    class Solution {
        fun largestTimeFromDigits(arr: IntArray): String {
            arr.sortDescending()

            for (h1 in arr.indices) {
                for (h2 in arr.indices) {
                    if (h2 == h1) {
                        continue
                    }

                    val hour = arr[h1] * 10 + arr[h2]

                    if (hour >= 24) {
                        continue
                    }
                    for (m1 in arr.indices) {
                        if (m1 == h1 || m1 == h2) {
                            continue
                        }
                        for (m2 in arr.indices) {
                            if (m2 == h1 || m2 == h2 || m2 == m1) {
                                continue
                            }

                            val minute = arr[m1] * 10 + arr[m2]

                            if (minute >= 60) {
                                continue
                            }

                            return "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
                        }
                    }
                }
            }

            return ""
        }
    }

    expect {
        Solution().largestTimeFromDigits(
            intArrayOf(2, 4, 6, 0)
        )
    }
}
