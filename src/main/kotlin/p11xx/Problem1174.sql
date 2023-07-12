WITH t AS
(SELECT order_date = customer_pref_delivery_date as p, ROW_NUMBER() OVER(PARTITION BY customer_id ORDER BY order_date) as r
FROM Delivery)
SELECT ROUND(SUM(t.p * 100) / COUNT(*), 2) AS immediate_percentage
FROM t
WHERE r = 1
