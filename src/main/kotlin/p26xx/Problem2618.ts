function checkIfInstanceOf(obj: any, classFunction: any): boolean {
    if (obj == null || obj == undefined || classFunction == null || classFunction == undefined) {
        return false
    }

    obj = obj.__proto__
    while (obj && obj !== classFunction.prototype) {
        obj = obj.__proto__
    }

    return obj === classFunction.prototype
};