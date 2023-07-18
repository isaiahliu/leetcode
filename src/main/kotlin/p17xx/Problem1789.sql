WITH t AS (SELECT employee_id, department_id, ROW_NUMBER() OVER(PARTITION BY employee_id ORDER BY primary_flag) AS r
FROM Employee)
SELECT employee_id, department_id
FROM t WHERE r = 1