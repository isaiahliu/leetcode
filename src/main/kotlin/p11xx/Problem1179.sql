SELECT
dp.id AS `id`,
jan.revenue AS `Jan_Revenue`,
feb.revenue AS `Feb_Revenue`,
mar.revenue AS `Mar_Revenue`,
apr.revenue AS `Apr_Revenue`,
may.revenue AS `May_Revenue`,
jun.revenue AS `Jun_Revenue`,
jul.revenue AS `Jul_Revenue`,
aug.revenue AS `Aug_Revenue`,
sep.revenue AS `Sep_Revenue`,
oct.revenue AS `Oct_Revenue`,
nov.revenue AS `Nov_Revenue`,
`dec`.revenue AS `Dec_Revenue`
FROM Department dp
LEFT JOIN Department jan ON dp.id = jan.id AND jan.month = 'Jan'
LEFT JOIN Department feb ON dp.id = feb.id AND feb.month = 'Feb'
LEFT JOIN Department mar ON dp.id = mar.id AND mar.month = 'Mar'
LEFT JOIN Department apr ON dp.id = apr.id AND apr.month = 'Apr'
LEFT JOIN Department may ON dp.id = may.id AND may.month = 'May'
LEFT JOIN Department jun ON dp.id = jun.id AND jun.month = 'Jun'
LEFT JOIN Department jul ON dp.id = jul.id AND jul.month = 'Jul'
LEFT JOIN Department aug ON dp.id = aug.id AND aug.month = 'Aug'
LEFT JOIN Department sep ON dp.id = sep.id AND sep.month = 'Sep'
LEFT JOIN Department oct ON dp.id = oct.id AND oct.month = 'Oct'
LEFT JOIN Department nov ON dp.id = nov.id AND nov.month = 'Nov'
LEFT JOIN Department `dec` ON dp.id = `dec`.id AND `dec`.month = 'Dec'
GROUP BY dp.id