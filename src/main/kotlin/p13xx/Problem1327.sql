SELECT p.product_name,
SUM(o.unit) AS `unit`
FROM Orders o
JOIN Products p ON o.product_id = p.product_id
WHERE LEFT(o.order_date, 7) = '2020-02'
GROUP BY o.product_id
HAVING SUM(unit) >= 100