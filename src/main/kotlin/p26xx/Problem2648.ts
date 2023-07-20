function* fibGenerator(): Generator<number, any, number> {
    let first = 0
    let second = 1

    while(true) {
        yield first

        const t = first + second
        first = second
        second = t
    }
};

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */