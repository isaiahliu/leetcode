type F = (x: number) => number;

function compose(functions: F[]): F {
	return function(x) {
        let result = x
        for(let index = functions.length - 1; index >= 0; index--) {
            result = functions[index](result)
        }

        return result
    }
};

/**
 * const fn = compose([x => x + 1, x => 2 * x])
 * fn(4) // 9
 */