// a clousure is a function with access to its outer function's scope even after the outer function has returned. 
// This allows the inner function to "remember" the state of variables from the outer function.
// a closure gives an inner function access to the outer function's scope even after the outer function has completely finished executing.

function outer() {
    let count = 0;

    return function inner() {
        count++;
        console.log(count);
    }
}

const counter = outer();

counter();
counter();
counter();

// Why is count still alive?

// Because JavaScript keeps the lexical environment alive as long as someone references it.