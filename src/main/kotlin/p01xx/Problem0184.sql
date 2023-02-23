WITH t AS (
    SELECT id AS `id`, dense_rank() OVER(PARTITION BY departmentId ORDER BY salary DESC) AS `r`
    FROM Employee
)
SELECT 
d.name AS `Department`,
e.name AS `Employee`,
e.salary AS `Salary`

FROM Employee e
JOIN Department d
ON e.departmentId = d.id
JOIN t ON e.id = t.id AND t.r = 1