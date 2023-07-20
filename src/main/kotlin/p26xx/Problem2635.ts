function map(arr: number[], fn: (n: number, i: number) => number): number[] {
    const result : number[] = []

    let index = 0
    while(index < arr.length) {
        result.push(fn(arr[index], index))

        index++
    }

    return result
};