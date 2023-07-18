WITH t AS (
    SELECT end.machine_id, end.timestamp - start.timestamp AS `time`
    FROM Activity end
    JOIN Activity start
    ON end.machine_id = start.machine_id AND end.process_id = start.process_id AND end.activity_type = 'end' AND start.activity_type = 'start'
)

SELECT machine_id, ROUND(SUM(time) / COUNT(*), 3) AS `processing_time`
FROM t
GROUP BY machine_id