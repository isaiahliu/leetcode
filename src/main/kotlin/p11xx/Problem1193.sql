SELECT LEFT(trans_date, 7) AS `month`,
country,
COUNT(*) AS `trans_count`,
SUM(state = 'approved') AS `approved_count`,
SUM(amount) AS `trans_total_amount`,
SUM(IF(state = 'approved', amount, 0)) AS `approved_total_amount`
FROM Transactions tx
GROUP BY LEFT(trans_date, 7), country