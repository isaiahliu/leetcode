WITH t AS (SELECT product_id, new_price, ROW_NUMBER() OVER(PARTITION BY product_id ORDER BY change_date DESC) AS 'r'
FROM Products
WHERE change_date <= '2019-08-16')
SELECT p.product_id, IFNULL(t.new_price, 10) AS `price`
FROM Products p
LEFT JOIN t ON p.product_id = t.product_id AND t.r = 1
GROUP BY p.product_id
