SELECT employee_id FROM (SELECT e.employee_id AS employee_id FROM Employees e LEFT JOIN Salaries s ON e.employee_id = s.employee_id WHERE s.employee_id IS NULL
UNION ALL
SELECT s.employee_id AS employee_id FROM Salaries s LEFT JOIN Employees e ON e.employee_id = s.employee_id WHERE e.employee_id IS NULL
) t
ORDER BY employee_id