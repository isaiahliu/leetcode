function chunk(arr: any[], size: number): any[][] {
    const result = []

    var currentSize = size

    let sub : any[]
    for(const item of arr){
        if(currentSize == size){
            sub = []
            result.push(sub)
            currentSize = 0
        }

        sub.push(item)
        currentSize++
    }

    return result
};