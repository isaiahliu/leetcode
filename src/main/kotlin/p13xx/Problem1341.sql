WITH u AS (
    SELECT u.name, ROW_NUMBER() OVER(ORDER BY COUNT(*) DESC, name) AS r
    FROM MovieRating mr
    JOIN Users u ON mr.user_id = u.user_id
    GROUP BY mr.user_id
), m AS (
    SELECT m.title, ROW_NUMBER() OVER(ORDER BY SUM(mr.rating) / COUNT(*) DESC, m.title) AS r
    FROM MovieRating mr
    JOIN Movies m ON mr.movie_id = m.movie_id
    WHERE LEFT(mr.created_at, 7) = '2020-02'
    GROUP BY mr.movie_id
)

SELECT name AS `results` FROM u WHERE r = 1
UNION ALL
SELECT title AS `results` FROM m WHERE r = 1