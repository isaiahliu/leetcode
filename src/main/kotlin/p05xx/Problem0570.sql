WITH t AS (
    SELECT managerId
    FROM Employee
    GROUP BY managerId
    HAVING COUNT(*) >= 5
)

SELECT e.name
FROM Employee e JOIN t ON e.id = t.managerId