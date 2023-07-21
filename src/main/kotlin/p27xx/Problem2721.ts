async function promiseAll<T>(functions: (() => Promise<T>)[]): Promise<T[]> {
    return new Promise<T[]>((resolve, reject) => {
        const result: T[] = []
        var count = 0
        for(let index = 0; index < functions.length; index++){
            const fun = functions[index]()

            fun.then(item => {
                count++
                result[index] = item

                if(count == functions.length){
                    resolve(result)
                }
            }).catch(error => reject(error))
        }
    })
};

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */