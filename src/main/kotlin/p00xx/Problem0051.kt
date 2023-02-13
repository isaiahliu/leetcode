package p00xx

fun main() {
    class Solution {
        fun solveNQueens(n: Int): List<List<String>> {
            val pos = arrayListOf(-1)

            val result = arrayListOf<List<String>>()

            while (pos.isNotEmpty()) {
                if (pos.size == n + 1) {
                    pos.removeAt(n)

                    result.add(pos.map { p ->
                        CharArray(n) {
                            if (p == it) 'Q' else '.'
                        }.joinToString("")
                    })
                }

                val last = pos.last()
                pos.remove(last)

                (last + 1 until n).firstOrNull { new ->
                    val index = pos.size

                    pos.filterIndexed { i, n ->
                        n == new || (index - i) == (n - new) || (index - i) == (new - n)
                    }.isEmpty()
                }?.let {
                    pos += it
                    pos += -1
                }
            }

            return result
        }
    }

    println(Solution().solveNQueens(1).size)
}

