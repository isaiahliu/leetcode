type Callback = (...args: any[]) => any;
type Subscription = {
    unsubscribe: () => void
}

class EventEmitter {
    map : Map<string, Set<Callback>> = new Map()
  subscribe(eventName: string, callback: Callback): Subscription {
    if(!this.map.has(eventName)){
        this.map.set(eventName, new Set())
    }

    this.map.get(eventName).add(callback)

    return {
      unsubscribe: () => {
          this.map.get(eventName).delete(callback)
      }
    };
  }

  emit(eventName: string, args: any[] = []): any[] {
      return [...(this.map.get(eventName)??[])].map(callback => callback(...args))
  }
}

/**
 * const emitter = new EventEmitter();
 *
 * // Subscribe to the onClick event with onClickCallback
 * function onClickCallback() { return 99 }
 * const sub = emitter.subscribe('onClick', onClickCallback);
 *
 * emitter.emit('onClick'); // [99]
 * sub.unsubscribe(); // undefined
 * emitter.emit('onClick'); // []
 */