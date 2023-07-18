SELECT r.employee_id, r.name, COUNT(*) AS `reports_count`, ROUND(AVG(e.age), 0) AS `average_age`
FROM Employees e
JOIN Employees r ON e.reports_to = r.employee_id
GROUP BY r.employee_id
ORDER BY r.employee_id