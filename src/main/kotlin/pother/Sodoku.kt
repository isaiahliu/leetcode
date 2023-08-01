package pother

fun main() {
    val game = game1.map { it.toCharArray() }.toTypedArray()

    fun Array<CharArray>.print(lastPos: Pair<Int, Int>? = null) {
        println("|-----------------|")
        forEachIndexed { r, row ->
            if (r == lastPos?.first && lastPos.second == 0) {
                print("(")
            } else {
                print("|")
            }
            row.forEachIndexed { c, ch ->
                print(ch)

                if (r == lastPos?.first && c == lastPos.second) {
                    print(")")
                } else if (c % 3 == 2) {
                    print("|")
                } else {
                    print(" ")
                }
            }
            println()

            if (r % 3 == 2) {
                if (r < 8) {
                    println("|-----+-----+-----|")
                } else {
                    println("|-----------------|")
                }
            }
        }
    }

    game.print(2 to 0)
}

private val game1 = arrayOf(
    "   4 25 3",
    " 4    9  ",
    "71      4",
    "  8     6",
    "   594 87",
    "    3    ",
    " 216 97  ",
    "37   5   ",
    "68   1   ",
)

