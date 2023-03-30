SELECT s1.id, COALESCE(s2.student, s3.student, s1.student) AS student
FROM Seat s1
LEFT JOIN Seat s2 ON s1.id + 1 = s2.id AND s1.id % 2 = 1
LEFT JOIN Seat s3 ON s1.id - 1 = s3.id AND s1.id % 2 = 0