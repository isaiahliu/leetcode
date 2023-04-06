WITH t AS (
    SELECT TIV_2015 FROM insurance
    GROUP BY TIV_2015
    HAVING COUNT(*) > 1
), p AS (
    SELECT LAT, LON FROM insurance
    GROUP BY LAT, LON
    HAVING COUNT(*) = 1
)

SELECT ROUND(SUM(TIV_2016), 2) AS `TIV_2016`
FROM insurance i
JOIN t ON i.TIV_2015 = t.TIV_2015
JOIN p ON i.LAT = p.LAT AND i.LON = p.LON