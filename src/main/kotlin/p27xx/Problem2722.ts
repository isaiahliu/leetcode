function join(arr1: any[], arr2: any[]): any[] {
    const map = [...arr1, ...arr2].reduce((total, item) => {
        const existing = total[item.id]

        if (existing){
            Object.assign(existing, item)
        } else {
            total[item.id] = item
        }

        return total
    }, {})

    return Object.values(map)
};