function isEmpty(obj: Record<string, any> | any[]): boolean {
    for (const item in obj){
        return false
    }

    return true
};