WITH t AS (
    SELECT product_id, year, quantity, price, RANK() OVER(PARTITION BY product_id ORDER BY year) AS r FROM Sales
)
SELECT product_id, year AS `first_year`, quantity, price
FROM t WHERE t.r = 1