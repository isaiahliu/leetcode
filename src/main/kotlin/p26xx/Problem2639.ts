function findColumnWidth(grid: number[][]): number[] {
    const result : number[] = []

    for (const row of grid){
        for (let i = 0; i < row.length; i++){
            result[i] = Math.max(result[i]??0, row[i].toString().length)
        }
    }

    return result
};