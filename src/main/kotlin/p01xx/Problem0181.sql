SELECT e.name AS `Employee`
FROM Employee e
JOIN Employee m
ON e.managerId = m.id AND e.salary > m.salary