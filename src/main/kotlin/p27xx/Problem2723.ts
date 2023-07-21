async function addTwoPromises(promise1: Promise<number>, promise2: Promise<number>): Promise<number> {
    return new Promise((resolve, reject) => {
        Promise.all([promise1, promise2]).then(items => resolve(items[0] + items[1]))
    })
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */