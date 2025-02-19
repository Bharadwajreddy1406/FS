const WebSocket = require('ws');

const ws = new WebSocket('ws://localhost:8080');

ws.on('open', () => {
    console.log('Connected to server');
    // Insert two employee records
    ws.send('INSERT John 5000');
    setTimeout(() => {
        ws.send('INSERT Jane 6000');
    }, 500);
    // Retrieve employee list after insertions
    setTimeout(() => {
        ws.send('RETRIEVE');
    }, 1000);
});

ws.on('message', (data) => {
    console.log('Received from server:', data);
});
