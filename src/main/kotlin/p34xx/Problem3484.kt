package p34xx

import util.expect

fun main() {
    class Spreadsheet(rows: Int) {
        val cells = hashMapOf<String, Int>()
        fun setCell(cell: String, value: Int) {
            cells[cell] = value
        }

        fun resetCell(cell: String) {
            cells.remove(cell)
        }

        fun getValue(formula: String): Int {
            return formula.split('=', '+').filter { it.isNotEmpty() }.sumOf {
                it.toIntOrNull() ?: cells[it] ?: 0
            }
        }
    }

    expect {
        Spreadsheet(1)
    }
}
