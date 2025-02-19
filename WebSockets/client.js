const WebSocket = require("ws");

const ws = new WebSocket("ws://localhost:8080");

ws.on("open", () => {
    console.log("Connected to server.");

    // Send test commands
    ws.send("INSERT Alice 50000");
    ws.send("INSERT Bob 60000");
    ws.send("RETRIEVE");
    ws.send("INVALID");
});

ws.on("message", (message) => {
    console.log(`Server Response: ${message}`);
});

ws.on("close", () => {
    console.log("Disconnected from server.");
});
