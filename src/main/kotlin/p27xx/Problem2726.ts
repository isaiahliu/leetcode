class Calculator {
    val : number
    constructor(value : number) {
        this.val = value
    }

    add(value : number) : Calculator {
        return new Calculator(this.val + value)
    }

    subtract(value : number) : Calculator {
        return new Calculator(this.val - value)
    }

    multiply(value : number) : Calculator {
        return new Calculator(this.val * value)
    }

    divide(value : number) : Calculator {
        if (value == 0){
            throw 'Division by zero is not allowed'
        }
        return new Calculator(this.val / value)
    }

    power(value : number) : Calculator {
        return new Calculator(this.val ** value)
    }

    getResult() : number {
        return this.val
    }
}