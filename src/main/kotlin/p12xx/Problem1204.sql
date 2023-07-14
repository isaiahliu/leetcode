SELECT p.person_name
FROM Queue p
JOIN Queue w
ON p.turn >= w.turn
GROUP BY p.person_id
HAVING SUM(w.weight) <= 1000
ORDER BY p.turn DESC
LIMIT 1