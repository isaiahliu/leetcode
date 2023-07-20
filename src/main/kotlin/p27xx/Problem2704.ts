type ToBeOrNotToBe = {
    toBe: (val: any) => boolean;
    notToBe: (val: any) => boolean;
};

function expect(val: any): ToBeOrNotToBe {
    const f = (predicate: boolean, error: string) => {
        if(predicate){
            return true
        } else {
            throw error
        }
    }

    return {
        toBe : (t) => f(t === val, "Not Equal"),
        notToBe : (t) => f(t !== val, "Equal")
    }
};

/**
 * expect(5).toBe(5); // true
 * expect(5).notToBe(5); // throws "Equal"
 */