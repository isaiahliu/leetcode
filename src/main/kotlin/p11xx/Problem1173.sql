SELECT ROUND(COUNT(IF(order_date = customer_pref_delivery_date, delivery_id, NULL)) / COUNT(*) * 100, 2) AS `immediate_percentage`
FROM Delivery