// <!--
// Employee Management WebSocket Application with MongoDB

// Objective:
// ----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
// The system should allow multiple clients to interact with a database to perform the following operations:
// 	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
// 	2. Retrieve Employee List (RETRIEVE)
// 	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)
	
// The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.

// Requirements:
// -------------
// Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections. (give a response as "Connected" )
// 		-> Process incoming commands from clients as discussed above.
// 		-> Log each received command on the console.
// 		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).
        
// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			                Server Response
// ============================================================================================
// INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
// INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

// RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

// RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


// INVALID					                "Invalid command."
// ============================================================================================

// Note: 
// -> Your implementation must use MongoDB for data persistence.
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080

// -->
const express = require('express')
const http = require('http')
const WebSocket = require('ws')
const mongoose = require('mongoose')
const AutoIncrement = require('mongoose-sequence')(mongoose)

mongoose.connect('mongodb://localhost:27017/employeeDB', { useNewUrlParser: true, useUnifiedTopology: true })

const employeeSchema = new mongoose.Schema({
	name: String,
	salary: Number,
	role: String,
	department: String,
	experience: Number
})
employeeSchema.plugin(AutoIncrement, { inc_field: 'employeeId' })
const Employee = mongoose.model('Employee', employeeSchema)

const app = express()
const server = http.createServer(app)
const wss = new WebSocket.Server({ server })

wss.on('connection', ws => {
	// ws.send("Connected")
    console.log("one guy connected")
	ws.on('message', async msg => {
		const parts = msg.toString().trim().split(" ")
		const command = parts[0]
		if(command === "INSERT" && parts.length === 6) {
			const [, name, salary, role, department, experience] = parts
			if(isNaN(salary) || isNaN(experience)) {
				ws.send("Invalid command.")
				return
			}
			try {
                const emp = new Employee({ name, salary: Number(salary), role, department, experience: Number(experience) })
                const savedEmp = await emp.save()
                ws.send("Employee inserted successfully. ID: 1")
			} catch(e) {
				ws.send("Error inserting employee.")
			}
		} else if(command === "RETRIEVE") {
			try {
				const emps = await Employee.find().sort({ employeeId: 1 })
                if(emps == null){
                    ws.send("ID: undefined")
                }
				emps.forEach(emp => {
					ws.send(`ID: ${emp.employeeId}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`)
				})
			} catch(e) {
				ws.send("Error retrieving employees.")
			}
		} else if(command === "RETRIEVE_BY_DEPT" && parts.length === 2) {
			const dept = parts[1]
			try {
				const emps = await Employee.find({ department: dept }).sort({ employeeId: 1 })
				emps.forEach(emp => {
					ws.send(`ID: ${emp.employeeId}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`)
				})
			} catch(e) {
				ws.send("Error retrieving employees.")
			}
		} else {
			ws.send("Invalid command.")
		}
	})
})

server.listen(8080)



