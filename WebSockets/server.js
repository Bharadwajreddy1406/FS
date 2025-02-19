const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8080 });
console.log('WebSocket server started on port 8080');

let employees = [];
let nextId = 1;

wss.on('connection', (ws) => {
    console.log('Client connected');
    ws.on('message', (message) => {
        const trimmed = message.toString().trim();
        const parts = trimmed.split(' ');
        if(parts[0] === 'INSERT' && parts.length === 3) {
            const name = parts[1];
            const salary = parseFloat(parts[2]);
            if(isNaN(salary)) {
                ws.send('INVALID');
                return;
            }
            const employee = { id: nextId++, name, salary };
            employees.push(employee);
            ws.send(`Inserted: ${employee.id} < ${employee.name}, ${employee.salary}`);
        }
        else if(trimmed.toUpperCase() === 'RETRIEVE') {
            if(employees.length === 0) {
                ws.send('No employees');
            } else {
                const list = employees.map(emp => `${emp.id} < ${emp.name}, ${emp.salary}`).join('\n');
                ws.send(list);
            }
        }
        else {
            ws.send('INVALID');
        }
    });
    ws.send('Welcome to the server!');
});
