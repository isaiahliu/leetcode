package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun containVirus(isInfected: Array<IntArray>): Int {
            var turn = 1
            val virusGroups = PriorityQueue<Pair<Pair<MutableSet<Pair<Int, Int>>, MutableSet<Pair<Int, Int>>>, Int>>(
                compareByDescending { it.first.second.size })

            fun preparedGroup(initR: Int, initC: Int) {
                isInfected[initR][initC]++

                val virusGroup = hashSetOf(initR to initC)
                val cleanGrids = hashSetOf<Pair<Int, Int>>()
                var wallCount = 0
                val tasks = hashSetOf(initR to initC)

                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                        arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                            isInfected.getOrNull(it.first)?.getOrNull(it.second)
                                .takeIf { it == 0 || it == turn } != null
                        }.forEach {
                            when (isInfected[it.first][it.second]) {
                                0 -> {
                                    cleanGrids.add(it)
                                    wallCount++
                                }

                                turn -> {
                                    if (virusGroup.add(it)) {
                                        isInfected[it.first][it.second]++
                                        tasks.add(it)
                                    }
                                }
                            }
                        }
                    }
                }

                if (wallCount > 0) {
                    virusGroups.add(virusGroup to cleanGrids to wallCount)
                }
            }

            fun infect() {
                virusGroups.forEach { (vc, wallSize) ->
                    vc.second.forEach { (r, c) ->
                        isInfected[r][c] = turn + 1
                    }
                }
            }

            fun scan() {
                virusGroups.clear()

                isInfected.forEachIndexed { r, row ->
                    row.forEachIndexed { c, num ->
                        when (num) {
                            turn -> {
                                preparedGroup(r, c)
                            }
                        }
                    }
                }
            }

            scan()

            var totalWallSize = 0

            while (virusGroups.isNotEmpty()) {
                virusGroups.poll().also {
                    it.first.first.forEach { (r, c) ->
                        isInfected[r][c] = -1
                    }
                    totalWallSize += it.second
                }

                infect()
                turn++
                scan()
            }

            return totalWallSize
        }
    }

    measureTimeMillis {
        Solution().containVirus(
            arrayOf(
                intArrayOf(0, 1, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 1, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}