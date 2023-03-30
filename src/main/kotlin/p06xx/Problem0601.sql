WITH t AS (SELECT s1.id AS `id1`, s2.id AS `id2`, s3.id AS `id3`
FROM Stadium s1
JOIN Stadium s2 ON s1.id + 1 = s2.id AND s2.people >= 100
JOIN Stadium s3 ON s2.id + 1 = s3.id AND s3.people >= 100
WHERE s1.people >=100)
SELECT s.*
FROM Stadium s JOIN t ON s.id IN (t.id1, t.id2, t.id3)
GROUP BY s.id
ORDER BY s.id