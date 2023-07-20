type Obj = Record<any, any>;

function compactObject(obj: Obj): Obj {
    if (typeof obj !== 'object' || obj === null){
        return obj
    } else if (Array.isArray(obj)){
        return obj.map(compactObject).filter(Boolean)
    } else {
        return Object.entries(obj).reduce((newObj, [key, value]) => {
            const co = compactObject(value)

            if(co){
                newObj[key] = co
            }

            return newObj
        }, {})
    }
};