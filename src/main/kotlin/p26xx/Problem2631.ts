declare global {
    interface Array<T> {
        groupBy(fn: (item: T) => string): Record<string, T[]>
    }
}

Array.prototype.groupBy = function(fn) {
    const result : Record<string, any[]> = {}

    for(const item of this){
        const r = fn(item)

        if(!result[r]) {
            result[r] = []
        }

        result[r].push(item)
    }

    return result
}

/**
 * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
 */