WITH t AS (
    SELECT requester_id AS p1, accepter_id AS p2 FROM RequestAccepted
    UNION
    SELECT accepter_id AS p1, requester_id AS p2 FROM RequestAccepted
)

SELECT t.p1 AS `id`, COUNT(*) AS `num`
FROM t
GROUP BY t.p1
ORDER BY COUNT(*) DESC
LIMIT 1