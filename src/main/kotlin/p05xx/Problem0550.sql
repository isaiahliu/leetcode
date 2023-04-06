WITH t AS(
    SELECT player_id, event_date, ROW_NUMBER() OVER(PARTITION BY player_id ORDER BY event_date) AS r
    FROM Activity
)

SELECT ROUND(SUM(CASE WHEN d2.player_id IS NOT NULL THEN 1 ELSE 0 END) / COUNT(*) ,2) AS fraction
FROM t
LEFT JOIN Activity d2
ON t.player_id = d2.player_id AND DATE_ADD(t.event_date, INTERVAL 1 DAY) = d2.event_date
WHERE t.r = 1