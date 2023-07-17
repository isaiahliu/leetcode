WITH d AS (
    SELECT DISTINCT visited_on AS `date`
    FROM Customer
)

SELECT d.date AS `visited_on`,
SUM(cm.amount) AS `amount`,
ROUND(SUM(cm.amount) / 7, 2) AS `average_amount`
FROM d
JOIN Customer cm ON cm.visited_on BETWEEN DATE_ADD(d.`date`, INTERVAL -6 DAY) AND d.`date`
GROUP BY d.date
HAVING COUNT(DISTINCT cm.visited_on) = 7
ORDER BY d.date