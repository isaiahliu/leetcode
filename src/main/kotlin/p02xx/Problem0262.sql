SELECT 
    request_at AS `Day`,
    ROUND(AVG(t.status != 'completed'), 2) AS `Cancellation Rate`
FROM Trips t
JOIN Users u ON t.client_id = u.users_id AND u.role = 'client' AND u.banned = 'No'
JOIN Users d ON t.driver_id = d.users_id AND d.role = 'driver' AND d.banned = 'No'
WHERE request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY request_at