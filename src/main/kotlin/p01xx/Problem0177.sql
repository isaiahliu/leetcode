CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  SET N = N - 1;
  RETURN (
      SELECT (SELECT distinct salary FROM Employee ORDER BY salary DESC limit N, 1) AS `SecondHighestSalary`
  );
END