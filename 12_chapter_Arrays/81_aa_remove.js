let arr=[1,2,3,4,5];
console.log(arr);
arr.push(6);
console.log(arr);
arr.pop();   //remove at the end of array
console.log(arr);

arr.push(6,7,8);
console.log(arr);

arr.unshift(0);  //add at the start of array
console.log(arr);

arr.shift();  //remove at the start of array
console.log(arr);

arr.splice(2,1);  //remove at index 2
console.log(arr);

arr.splice(2,0,99);  //add at index 2
console.log(arr);

arr.splice(2,1,100);  //replace at index 2
console.log(arr);

arr.splice(2,2,200,300);  //replace at index 2 and 3
console.log(arr);
