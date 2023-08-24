package p20xx

import util.expect

fun main() {
    class Robot(val width: Int, val height: Int) {
        val directions = arrayOf(
            1 to 0 to "East",
            0 to 1 to "North",
            -1 to 0 to "West",
            0 to -1 to "South"
        )

        val position = intArrayOf(0, 0)

        var direction = 0

        val round = (width + height - 2) * 2

        fun step(num: Int) {
            var remain = num % round + (num / round).coerceAtMost(1) * round

            while (remain > 0) {
                val (deltaX, deltaY) = directions[direction].first

                position[0] += deltaX
                position[1] += deltaY

                if (position[0] in 0 until width && position[1] in 0 until height) {
                    remain--
                } else {
                    position[0] -= deltaX
                    position[1] -= deltaY
                    direction = (direction + 1) % 4
                }
            }
        }

        fun getPos(): IntArray {
            return position
        }

        fun getDir(): String {
            return directions[direction].second
        }
    }

    val robot = Robot(97, 98)
    robot.step(66392)
    robot.step(83376)
    robot.step(71796)
    robot.step(57514)
    robot.step(36284)
    robot.step(69866)
    robot.step(31652)
    robot.step(32038)
    expect {
        robot.getDir()
    }

    expect {
        robot.getPos().toList()
    }
}