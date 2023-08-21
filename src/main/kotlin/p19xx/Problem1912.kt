package p19xx

import java.util.*
import util.expect

fun main() {
    class MovieRentingSystem(n: Int, val entries: Array<IntArray>) {
        val ready = hashMapOf<Int, TreeSet<Int>>()
        val rent = TreeSet(compareBy<Int> { entries[it][2] }.thenBy { entries[it][0] }.thenBy { entries[it][1] })

        val indices = hashMapOf<Pair<Int, Int>, Int>()

        init {
            entries.forEachIndexed { index, (shop, movie, _) ->
                indices[shop to movie] = index
                ready.computeIfAbsent(movie) { TreeSet(compareBy<Int> { entries[it][2] }.thenBy { entries[it][0] }) }
                    .add(index)
            }
        }

        fun search(movie: Int): List<Int> {
            return ready[movie]?.take(5)?.map { entries[it][0] }.orEmpty()
        }

        fun rent(shop: Int, movie: Int) {
            indices[shop to movie]?.also {
                ready[movie]?.remove(it)
                rent.add(it)
            }
        }

        fun drop(shop: Int, movie: Int) {
            indices[shop to movie]?.also {
                rent.remove(it)
                ready[movie]?.add(it)
            }
        }

        fun report(): List<List<Int>> {
            return rent.take(5).map { listOf(entries[it][0], entries[it][1]) }
        }
    }

    expect {
        MovieRentingSystem(1, arrayOf()).search(
            1
        )
    }
}