function cancellable<T>(generator: Generator<Promise<any>, T, unknown>): [() => void, Promise<T>] {
    let cancelled = false
    const cancel = () => {
        cancelled = true
    }

    const process = async () => {
        let resp = generator.next()
        while(!resp.done) {
            try {
                const value = await resp.value
                if(cancelled){
                    resp = generator.throw("Cancelled")
                } else {
                    resp = generator.next(value)
                }
            } catch (e) {
                resp = generator.throw(e)
            }
        }

        return resp.value
    }

    return [cancel, process()]
};

/**
 * function* tasks() {
 *   const val = yield new Promise(resolve => resolve(2 + 2));
 *   yield new Promise(resolve => setTimeout(resolve, 100));
 *   return val + 1;
 * }
 * const [cancel, promise] = cancellable(tasks());
 * setTimeout(cancel, 50);
 * promise.catch(console.log); // logs "Cancelled" at t=50ms
 */