package p26xx

import util.expect

fun main() {
    class FrequencyTracker {
        val nums = hashMapOf<Int, Int>()
        val frequencies = hashMapOf<Int, Int>()

        private fun MutableMap<Int, Int>.inc(n: Int): Int {
            val count = (this[n] ?: 0) + 1

            this[n] = count

            return count
        }

        private fun MutableMap<Int, Int>.dec(n: Int): Int {
            return this[n]?.let {
                if (it > 1) {
                    this[n] = it - 1
                } else {
                    this -= n
                }

                it - 1
            } ?: 0
        }

        fun add(number: Int) {
            val freq = nums.inc(number)

            frequencies.dec(freq - 1)
            frequencies.inc(freq)
        }

        fun deleteOne(number: Int) {
            nums[number]?.also {
                nums.dec(number)

                frequencies.dec(it)
                frequencies.inc(it - 1)
            }
        }

        fun hasFrequency(frequency: Int): Boolean {
            return frequency in frequencies
        }
    }

    expect {
        FrequencyTracker()
    }
}