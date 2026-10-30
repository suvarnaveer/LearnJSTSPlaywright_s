function start() {
    let name="pramod";
    function displayName() {
        console.log(name);
    }
    return displayName;
}
let redisplayName = start();
redisplayName();