class TimeLimitedCache {
    constructor() {

    }
    //key, value - version
    map: {[key:number] : [number, number]} = {};

    set(key: number, value: number, duration: number): boolean {
        const existing = this.map[key]

        let version = 1
        if (existing) {
            version = existing[1] + 1
        }

        this.map[key] = [value, version]
        setTimeout(() => {
            const v = this.map[key]

            if(v && v[1] == version){
                delete this.map[key]
            }
        }, duration)

        return existing != null
    }

    get(key: number): number {
        const value = this.map[key]
        return (value && value[0])??-1
    }

	count(): number {
        return Object.entries(this.map).length
    }
}

/**
 * Your TimeLimitedCache object will be instantiated and called as such:
 * var obj = new TimeLimitedCache()
 * obj.set(1, 42, 1000); // false
 * obj.get(1) // 42
 * obj.count() // 1
 */