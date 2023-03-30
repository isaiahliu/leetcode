SELECT n.id, CASE WHEN n.p_id IS NULL THEN 'Root' WHEN COUNT(c.id) = 0 THEN 'Leaf' ELSE 'Inner' END AS Type
FROM tree n
LEFT JOIN tree c ON c.p_id = n.id
GROUP BY n.id
ORDER BY id
