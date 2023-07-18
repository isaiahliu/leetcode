WITH t AS (SELECT employee_id, department_id, ROW_NUMBER() OVER(PARTITION BY employee_id ORDER BY IF(primary_flag = 'Y', 0, 1)) AS r
FROM Employee)
SELECT employee_id, department_id
FROM t WHERE r = 1