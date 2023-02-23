WITH t AS 
(SELECT id, ROW_NUMBER() OVER(PARTITION BY email ORDER BY id) AS r
FROM Person)
DELETE p 
FROM Person p
JOIN t ON p.id = t.id AND t.r > 1
