package p02xx

fun main() {
    class MedianFinder {
        val nums = arrayListOf<Int>()

        fun addNum(num: Int) {
            var index = 0
            while (index < nums.size && num < nums[index]) {
                index++
            }

            nums.add(index, num)
        }

        fun findMedian(): Double {
            val size = nums.size
            return if (size % 2 == 1) {
                nums[size / 2].toDouble()
            } else {
                (nums[size / 2 - 1] + nums[size / 2]) / 2.0
            }
        }
    }

    MedianFinder().also {
        it.addNum(1)
        it.addNum(2)
        it.addNum(3)

        println(it.findMedian())
    }
}

