SELECT stock_name, SUM(IF(operation = 'Sell', price, -price)) AS `capital_gain_loss`
FROM Stocks s
GROUP BY stock_name