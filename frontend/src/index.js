//strict mode
"use strict";

const API_URL = "http://localhost:8080/eztasker";
//first..insert username to get his list of tasks
//second..show all tasks in the list and open submiting area

const appS = document.getElementById("appSection");
const loginS = document.getElementById("loginSection");
const login = document.querySelector(".loginButton");

login.addEventListener("click", function () {
  //loginS.style.visibility = "hidden";
  loginS.style.display = "none";
  appS.style.display = "flex";
  // Get the username from the input
  var username = document.getElementById("username").value;
  console.log(API_URL + "/users/check");
  fetch(API_URL + "/users/check", {
    method: "GET",
  }).then((response) => console.log(response));
});

// create post request to the server
//   fetch(API_URL + "/login", {
//     method: "POST",
//     body: JSON.stringify({
//       username: username,
//     }),
//     headers: {
//       "content-type": "application/json",
//     },
//   })
//     .then((response) => response.json())
//     .then((data) => {
//       console.log(data);

//       // hide login section and show app section
//       if (data === "Exist") {
//         alert("Welcome back");
//       } else {
//         alert("Welcome");
//       }

//       loginS.style.display = "none";
//       appS.style.display = "block";
//     })
//     .catch((error) => {
//       console.error("Error:", error);
//       // Handle error if needed
//     });
// });

// document.querySelector("form").addEventListener("submit", function (event) {
//   // Prevent the form from being submitted
//   event.preventDefault();

//   // Get the task name and description
//   var taskName = document.getElementById("taskName").value;
//   var taskDescription = document.getElementById("taskDescription").value;
//   var dueDate = document.getElementById("dueDate").value;
//   //create post request to the server

//   fetch(API_URL + "/addtask", {
//     method: "POST",
//     body: JSON.stringify({
//       taskName: taskName,
//       taskDescription: taskDescription,
//       dueDate: dueDate,
//     }),
//     headers: {
//       "content-type": "application/json",
//     },
//   })
//     .then((response) => response.json())
//     .then((data) => console.log(data));
//   //Update list
//   //updateList();
//   function updateList() {
//     fetch(API_URL + "/getList", {
//       method: "GET",
//       headers: {
//         "content-type": "application/json",
//       },
//     })
//       .then((response) => response.json())
//       .then((data) => console.log(data));
//     //check if table change if so update list
//   }
// });//});

// // Create a new list item and add a class to it
// var li = document.createElement("li");
// li.className = "task-item";

// // Create the task name and description elements
// var taskNameElement = document.createElement("h3");
// taskNameElement.textContent = taskName;
// var taskDescriptionElement = document.createElement("p");
// taskDescriptionElement.textContent = taskDescription;

// // Add the task name and description to the list item
// li.appendChild(taskNameElement);
// li.appendChild(taskDescriptionElement);

// // Add the list item to the task list
// document.getElementById("taskList").appendChild(li);

// // Clear the form
// document.getElementById("taskName").value = "";
// document.getElementById("taskDescription").value = ""
