type F = (...p: any[]) => any

function debounce(fn: F, t: number): F {
    let version = 0
    return function(...args) {
        let v = ++version
        setTimeout(()=> {
            if(v == version){
                fn(...args)
            }
        }, t)
    }
};

/**
 * const log = debounce(console.log, 100);
 * log('Hello'); // cancelled
 * log('Hello'); // cancelled
 * log('Hello'); // Logged at t=100ms
 */