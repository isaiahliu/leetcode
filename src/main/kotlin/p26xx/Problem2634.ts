function filter(arr: number[], fn: (n: number, i: number) => any): number[] {
    const result : number[] = []

    let index = 0
    while(index < arr.length) {
        if(fn(arr[index], index)){
            result.push(arr[index])
        }

        index++
    }

    return result
};