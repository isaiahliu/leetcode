SELECT c.Name AS `Customers`
FROM Customers c
LEFT JOIN Orders o
ON c.id = o.CustomerId
WHERE o.Id IS NULL