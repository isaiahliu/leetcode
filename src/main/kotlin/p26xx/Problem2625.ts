type MultiDimensionalArray = (number | MultiDimensionalArray)[];

var flat = function (arr:  MultiDimensionalArray, n: number):  MultiDimensionalArray {
    if(n == 0){
        return arr
    }

    const result : MultiDimensionalArray = []

    for(let item of arr){
        if(typeof (item) === 'number'){
            result.push(item)
        } else {
            result.push(...flat(item, n-1))
        }
    }

    return result
};