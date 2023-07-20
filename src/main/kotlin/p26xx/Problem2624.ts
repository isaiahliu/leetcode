declare global {
  interface Array<T> {
    snail(rowsCount: number, colsCount: number): number[][];
  }
}

Array.prototype.snail = function(rowsCount: number, colsCount: number): number[][] {
    if(this.length != rowsCount * colsCount){
        return []
    }

    const result : number[][] = Array(rowsCount).fill(0).map(() => [])

    let r = 0
    let c = 0
    let deltaR = 1

    let index = 0
    while(index < this.length){
        result[r][c] = this[index++]

        r += deltaR

        if(r < 0 || r >= rowsCount) {
            c++
            deltaR = -deltaR
            r += deltaR
        }
    }

    return result
}

/**
 * const arr = [1,2,3,4];
 * arr.snail(1,4); // [[1,2,3,4]]
 */