console.log("" == 0); // // true, "" coerced to Number → 0
console.log("0" == 0);  // true, "0" coerced to Number → 0
console.log("" == "0"); // false, both strings, compared as-is
// (transitivity broken) -> coerced


// === fixes it
console.log("" === 0);       // false
console.log("0" === 0);      // false
console.log("" === "0");     // false