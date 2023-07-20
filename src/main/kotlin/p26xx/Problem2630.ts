type Fn = (...params: any) => any

function memoize(fn: Fn): Fn {
    const cache = new Map<any, any>()

    const valueKey = "CACHE_VALUE"
    return function(...params: any) {
        let c = cache

        for(const param of params){
            if(!c.has(param)){
                c.set(param, new Map<any,any>())
            }

            c = c.get(param)
        }

        if(valueKey in c){
            return c[valueKey]
        }else{
            const result = fn(...params)

            c[valueKey] = result

            return result
        }
    }
}


/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */