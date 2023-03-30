SELECT n.id, CASE WHEN p.id IS NULL THEN 'Root' WHEN COUNT(c.id) = 0 THEN 'Leaf' ELSE 'Inner' END AS Type
FROM tree n
LEFT JOIN tree p ON n.p_id = p.p_id
LEFT JOIN tree c ON c.p_id = n.id
GROUP BY n.id
ORDER BY id
