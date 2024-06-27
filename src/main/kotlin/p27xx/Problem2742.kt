package p27xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun paintWalls(cost: IntArray, time: IntArray): Int {
            val dp = Array(cost.size + 1) {
                TreeMap<Int, Int>()
            }

            dp[0][0] = 0

            cost.indices.forEach {
                val current = dp[it + 1]
                val pre = dp[it]

                pre.forEach { (remainTime, cost) ->
                    current[remainTime - 1] = cost
                }

                pre.forEach { (remainTime, c) ->
                    val newCost = c + cost[it]
                    val newTime = maxOf(minOf(remainTime + time[it], cost.size), -cost.size)

                    while (true) {
                        current.floorEntry(newTime)?.takeIf { it.value >= newCost }?.also {
                            current.remove(it.key)
                        } ?: break
                    }

                    if (current.ceilingEntry(newTime)?.takeIf { it.value <= newCost } == null) {
                        current[newTime] = newCost
                    }
                }
            }

            return dp.last().ceilingEntry(0).value
        }
    }

    expect(668599) {
        Solution().paintWalls(
            intArrayOf(
                31461,
                142243,
                67461,
                29387,
                160165,
                43932,
                117588,
                67386,
                14104,
                153271,
                54339,
                6810,
                95676,
                119600,
                16146,
                93369,
                28624,
                4047,
                79960,
                171364,
                156936,
                77370,
                141627,
                159313,
                63928,
                110166,
                43019,
                20230,
                45900,
                108944,
                120970,
                71991,
                164394,
                157572,
                54750,
                50961,
                32152,
                21710,
                74973,
                70011,
                23374,
                155596,
                38022,
                13882,
                18685,
                106774,
                122584,
                47416,
                120421,
                154419,
                73086,
                64841,
                116218,
                20653,
                52992,
                37097,
                62453,
                109228,
                93045,
                106884,
                53948,
                13803,
                20767,
                122728,
                138287,
                145280,
                57258,
                140370,
                30832,
                82212,
                107388,
                3924,
                169952,
                83079,
                11663,
                63793,
                117664,
                72265,
                48779,
                67944,
                160573,
                50810,
                33756,
                58351,
                7250,
                51436,
                130605,
                156001,
                144418,
                69934,
                155348,
                153278,
                27589,
                141267,
                84631,
                59999,
                95595,
                108093,
                128331,
                27341,
                107521,
                55582,
                44240,
                88555,
                1417,
                31397,
                58580,
                75805,
                92846,
                133795,
                83300,
                148635,
                99882,
                61129,
                123584,
                38252,
                144746,
                31509,
                130810,
                38856,
                88356,
                14110,
                13143,
                61724,
                55628,
                12185,
                27516,
                3866,
                166496,
                169232,
                68344,
                160854,
                52617,
                132631,
                88152,
                25744,
                99766,
                9734,
                70601,
                57322,
                89413,
                140354,
                45876,
                74326,
                129233,
                19329,
                27259,
                82776,
                151270,
                130560,
                46975,
                37482,
                4261,
                132459,
                167080,
                160933,
                82832,
                111287,
                2556,
                95337,
                123903,
                20313,
                98017,
                101035,
                62943,
                147097,
                19511,
                103788,
                85397,
                18249,
                63913,
                126398,
                27763,
                17914,
                39686,
                41561,
                93390,
                25619,
                33227,
                23996,
                161155,
                159357,
                124041,
                16617,
                92983,
                111814,
                162801,
                30053,
                91964,
                100263,
                163525,
                165857,
                28673,
                121284,
                128941,
                131562,
                72917,
                8340,
                66775,
                160372,
                115068,
                159578,
                159213,
                146438,
                142441,
                129446,
                102200,
                120581,
                128494,
                131443,
                90885,
                2379,
                31440,
                54524,
                122397,
                160060,
                169369,
                129323,
                163542,
                143272,
                122653,
                79877,
                12403,
                89696,
                66196,
                66311,
                30679,
                172933,
                127200,
                36396,
                75613,
                68019,
                38401,
                76847,
                109229,
                10184,
                162308,
                145632,
                55744,
                162486,
                125826,
                567,
                13438,
                28713,
                74053,
                114055,
                37853,
                48021,
                82759,
                126772,
                150400,
                26165,
                40610,
                91643,
                100172,
                131423,
                107808,
                149571,
                66085,
                44777,
                87457,
                27106,
                44304,
                98037,
                93913,
                161692,
                29105,
                64551,
                42051,
                71769,
                101633,
                151301,
                13332,
                17972,
                79749,
                31108,
                100653,
                162980,
                9401,
                45695,
                2113,
                50338,
                53688,
                2951,
                66102,
                132196,
                5355,
                137406,
                52672,
                172467,
                141958,
                70781,
                144579,
                17097,
                58722,
                41814,
                2726,
                134267,
                85094,
                114169,
                133646,
                86242,
                70289,
                131659,
                130874,
                157621,
                130846,
                140755,
                44073,
                126518,
                117482,
                127343,
                165325,
                120112,
                136611,
                152115,
                914,
                138924,
                126253,
                8621,
                117235,
                80088,
                42318,
                166927,
                77802,
                149901,
                77798,
                104700,
                113494,
                129403,
                53818,
                26926,
                55982,
                30033,
                132363,
                76279,
                31903,
                153090,
                108189,
                6582,
                2469,
                141434,
                39370,
                110424,
                151066,
                24667,
                65116,
                50160,
                102184,
                101388,
                145549,
                152353,
                140947,
                27268,
                39368,
                63057,
                112926,
                6203,
                87052,
                79507,
                109886,
                105953,
                135124,
                133619,
                95625,
                28601,
                17293,
                53784,
                60641,
                100431,
                146312,
                77464,
                42896,
                53479,
                152298,
                63662,
                75289,
                37461,
                35503,
                65238,
                120063,
                92557,
                74751,
                34428,
                40681,
                7260,
                43519,
                4047,
                17044,
                1083,
                140466,
                149872,
                124372,
                111635,
                92205,
                134075,
                137624,
                101803,
                63290,
                10508,
                33177,
                119344,
                27936,
                166211,
                96466,
                4558,
                63783,
                73564,
                153598,
                36138,
                84636,
                155674,
                130733,
                130813,
                66759,
                16999
            ), intArrayOf(
                2,
                10,
                8,
                10,
                4,
                2,
                9,
                2,
                2,
                8,
                1,
                10,
                10,
                5,
                7,
                3,
                10,
                1,
                6,
                2,
                5,
                9,
                10,
                5,
                10,
                4,
                5,
                6,
                9,
                3,
                3,
                1,
                2,
                3,
                3,
                10,
                2,
                7,
                2,
                10,
                3,
                2,
                6,
                1,
                7,
                10,
                3,
                8,
                1,
                2,
                10,
                5,
                10,
                7,
                3,
                9,
                2,
                9,
                1,
                2,
                1,
                7,
                5,
                5,
                10,
                2,
                10,
                2,
                8,
                10,
                5,
                10,
                1,
                3,
                2,
                9,
                2,
                4,
                6,
                3,
                4,
                5,
                9,
                2,
                10,
                7,
                3,
                8,
                7,
                2,
                4,
                9,
                7,
                6,
                3,
                6,
                1,
                1,
                4,
                4,
                2,
                8,
                8,
                1,
                3,
                9,
                9,
                7,
                10,
                4,
                9,
                1,
                4,
                7,
                3,
                8,
                1,
                7,
                6,
                4,
                8,
                9,
                9,
                4,
                4,
                4,
                3,
                2,
                3,
                2,
                9,
                7,
                1,
                3,
                2,
                9,
                2,
                6,
                6,
                6,
                6,
                9,
                7,
                7,
                3,
                2,
                2,
                4,
                8,
                2,
                7,
                9,
                6,
                4,
                8,
                6,
                10,
                1,
                10,
                6,
                7,
                7,
                3,
                8,
                10,
                5,
                7,
                10,
                2,
                3,
                4,
                6,
                9,
                10,
                4,
                2,
                7,
                4,
                3,
                1,
                4,
                7,
                3,
                8,
                10,
                6,
                2,
                6,
                5,
                7,
                8,
                10,
                10,
                7,
                1,
                7,
                2,
                2,
                3,
                9,
                2,
                5,
                4,
                4,
                6,
                2,
                2,
                6,
                8,
                2,
                8,
                5,
                5,
                6,
                10,
                6,
                5,
                4,
                5,
                7,
                6,
                3,
                1,
                3,
                10,
                3,
                3,
                10,
                9,
                5,
                3,
                3,
                3,
                7,
                1,
                2,
                8,
                10,
                8,
                4,
                2,
                4,
                7,
                7,
                7,
                6,
                5,
                4,
                6,
                3,
                5,
                3,
                3,
                5,
                6,
                3,
                9,
                7,
                8,
                8,
                9,
                9,
                9,
                3,
                9,
                5,
                1,
                5,
                7,
                8,
                2,
                8,
                9,
                2,
                3,
                10,
                8,
                8,
                4,
                10,
                6,
                7,
                9,
                3,
                5,
                1,
                4,
                4,
                7,
                7,
                1,
                4,
                5,
                1,
                5,
                6,
                2,
                4,
                2,
                6,
                10,
                10,
                4,
                3,
                5,
                2,
                8,
                10,
                2,
                9,
                3,
                10,
                1,
                9,
                5,
                2,
                10,
                10,
                5,
                9,
                3,
                2,
                2,
                6,
                10,
                7,
                2,
                8,
                4,
                10,
                2,
                5,
                8,
                10,
                7,
                8,
                3,
                7,
                3,
                8,
                3,
                6,
                6,
                7,
                7,
                1,
                1,
                8,
                9,
                6,
                2,
                8,
                8,
                3,
                6,
                4,
                10,
                5,
                5,
                10,
                3,
                6,
                3,
                2,
                4,
                6,
                9,
                9,
                5,
                7,
                9,
                10,
                5,
                7,
                1,
                5,
                9,
                3,
                10,
                4,
                8,
                9,
                6,
                2,
                5,
                1,
                7,
                6,
                2,
                6,
                8,
                8,
                5,
                7,
                3,
                2,
                5,
                5,
                5,
                9,
                3,
                1,
                7,
                7,
                7,
                6,
                8,
                6,
                8,
                5,
                4,
                6,
                7,
                1,
                1,
                8
            )
        )
    }
}
