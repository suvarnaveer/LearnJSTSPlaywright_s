let tests=["pass", "fail", "pass", "pass", "fail"];
for (let i=0; i<tests.length; i++){
    const element=tests[i];
    console.log(i, element);
}

for(let test of tests){
    console.log(test);
}

