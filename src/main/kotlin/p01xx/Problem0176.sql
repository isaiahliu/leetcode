SELECT (SELECT distinct salary FROM Employee ORDER BY salary DESC limit 1,1) AS `SecondHighestSalary`
