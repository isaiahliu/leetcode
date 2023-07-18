SELECT contest_id, ROUND(IFNULL(COUNT(*), 0) / (SELECT COUNT(*) FROM Users) * 100, 2) AS `percentage`
FROM Register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id