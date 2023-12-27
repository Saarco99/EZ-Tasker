//strict mode
"use strict";
class Task {
  constructor(id, title, discription, dueDate, status) {
    this.id = id;
    this.title = title;
    this.discription = discription;
    this.dueDate = dueDate;
    this.status = status;
  }
}

const API_URL = "http://localhost:8080/eztasker";
//first..insert username to get his list of tasks
//second..show all tasks in the list and open submiting area

const appS = document.getElementById("appSection");
const loginS = document.getElementById("loginSection");
const login = document.querySelector(".loginButton");
const loggedInUser = document.getElementById("loggedInUser");
var currUser = {
  username: "",
  id: "",
};
// Resizeable section

appS.style.resize = "both";
//appS.style.overflow = "auto";
//set option to resize the app section

loggedInUser.textContent = ""; //textbox for username
function updateLoggedInUser() {
  loggedInUser.textContent = currUser.username;
}
updateLoggedInUser();

//login
login.addEventListener("click", function () {
  //loginS.style.visibility = "hidden";

  // Get the username from the input
  var username = document.getElementById("username").value;
  if (!username.trim()) {
    alert("Please enter a valid username.");
    return;
  }
  currUser.username = username;
  fetch(API_URL + "/users/register", {
    method: "POST",
    body: JSON.stringify(username),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Registration/Login failed.");
      }
      const userId = getUserId(username);
      currUser.id = userId;
      return response.json();
    })
    .then((userData) => {
      console.log("User logged in or registered successfully:", userData);
      const userId = getUserId(username);
      currUser.id = userId;
      updateLoggedInUser();
      setupTaskTable();
      updateTaskTable();
    })
    .catch((error) => {
      // console.error("Error:", error);
      // Handle error if needed
    });

  loginS.style.display = "none";
  appS.style.display = "flex";
});

//add task
document.querySelector("form").addEventListener("submit", function (event) {
  // Prevent the form from being submitted
  event.preventDefault();
  //maybe add to body the session id or the username
  var username = document.getElementById("username").value;
  var taskName = document.getElementById("task-name").value;
  var taskDescription = document.getElementById("task-description").value;
  var dueDate = document.getElementById("task-due-date").value;
  //create post request to the server

  fetch(API_URL + "/tasks/addtask", {
    method: "POST",
    body: JSON.stringify({
      username: username,
      taskName: taskName,
      taskDescription: taskDescription,
      dueDate: dueDate,
    }),
    headers: {
      "content-type": "application/json",
    },
  })
    .then((response) => response.json())
    .then(updateTaskTable());
  //Update list
  //updateList();
});

function setupTaskTable() {
  if (!currUser.id) {
    fetch(API_URL + "/tasks/all/" + currUser.id, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        username: username,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        // Clear the table container
        $("#taskContainer").empty();

        // Create a table dynamically
        let table = $("<table></table>")
          .addClass("display")
          .attr("id", "taskTable");

        // Append table to a container
        $("#taskContainer").append(table);

        // Initialize DataTables
        $("#taskTable").DataTable({
          destroy: true,
          data: data,
          columns: [
            { title: "Task ID", data: "id" },
            { title: "Task Name", data: "name" },
            { title: "Due Date", data: "dueDate" },
            { title: "Hours Left", data: "hour" },
            { title: "Status", data: "status" },
            { title: "Done", data: "done" },
          ],
        });
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }
}

function getUserId(username) {
  console.log(API_URL + "/users/id/" + username);
  fetch(API_URL + "/users/id/" + username, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Registration/Login failed.");
      }
      return response.json();
    })
    .then((userData) => {
      console.log("User logged in with his id of: ", userData);
      currUser.id = userData;
      return userData.id;
    })
    .catch((error) => {
      // console.error("Error:", error);
      // Handle error if needed
    });
}
function createTaskDetailsSection() {
  const taskDetailsSection = document.createElement("section");
  taskDetailsSection.classList.add("task-details-section");
  appS.insertBefore(taskDetailsSection, appS.firstChild);
  return taskDetailsSection; // return the created section
}

function showTaskDetails(task) {
  //check if section already exists
  let taskDetailsSection = document.querySelector(".task-details-section");
  if (!taskDetailsSection) {
    taskDetailsSection = createTaskDetailsSection();
  }

  const detailsContainer = document.createElement("div");
  detailsContainer.classList.add("task-details");

  // Add the task details to the container
  const title = document.createElement("h2");
  title.textContent = task.title;
  detailsContainer.appendChild(title);

  const description = document.createElement("p");
  description.textContent = task.description;
  detailsContainer.appendChild(description);

  const dueDate = document.createElement("p");
  dueDate.textContent = "Due Date: " + task.dueDate;
  detailsContainer.appendChild(dueDate);

  const status = document.createElement("p");
  status.textContent = "Status: " + task.status;
  detailsContainer.appendChild(status);

  // Create a close button for the task details section
  const closeButton = document.createElement("button");
  closeButton.textContent = "Close";
  closeButton.addEventListener("click", function () {
    detailsContainer.remove();
  });

  // Append the task details to the task details section
  detailsContainer.appendChild(closeButton);
  taskDetailsSection.appendChild(detailsContainer); // append the detailsContainer to the taskDetailsSection
}

function updateTaskTable() {
  if (!currUser.id) {
    return;
  } else {
    // Fetch the tasks from the server
    fetch(API_URL + "/tasks/all/" + currUser.id, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (Array.isArray(data) && data.length) {
          // Create an array of Task objects

          let tasks = data.map(
            (item) =>
              //calculate hours left
              new Task(
                item.id,
                item.title,
                item.description,
                item.dueDate,
                item.status,
                item.createdAt
              )
          );

          // Get the table body
          let tableBody = document.getElementById("taskTableBody");

          // Clear the table body
          tableBody.innerHTML = "";

          // Add tasks to the table body

          tasks.forEach((task) => {
            let row = document.createElement("tr");
            row.addEventListener("click", function () {
              showTaskDetails(task);
            });

            let idCell = document.createElement("td");
            idCell.textContent = task.id;
            row.appendChild(idCell);

            let titleCell = document.createElement("td");
            titleCell.textContent = task.title;
            row.appendChild(titleCell);
            //in a new popup window
            // let descriptionCell = document.createElement("td");
            // descriptionCell.textContent = task.description;
            // row.appendChild(descriptionCell);

            let dueDateCell = document.createElement("td");
            let fullDate = new Date(task.dueDate);
            dueDateCell.textContent = fullDate.toLocaleDateString();
            row.appendChild(dueDateCell);
            // Set default date to get the current date instead of 1970

            let currentDate = new Date();
            currentDate.setHours(0, 0, 0, 0);

            // Calculate hours left until due date
            //let currentDate = new Date();
            let dueDate = new Date(task.dueDate);
            let timeLeftCell = document.createElement("td");
            if (dueDate < currentDate) {
              timeLeftCell.textContent = "Overdue";
              //paint it red
              timeLeftCell.style.color = "red";
              row.appendChild(timeLeftCell);
            } else {
              let timeLeft = Math.ceil(
                (dueDate - currentDate) / (1000 * 60 * 60) - 2
                //for il time
              );

              timeLeftCell.textContent = timeLeft;
              row.appendChild(timeLeftCell);
            }

            let statusCell = document.createElement("td");
            statusCell.textContent = task.status;
            row.appendChild(statusCell);

            let doneCell = document.createElement("td");
            let doneCheckbox = document.createElement("input");
            doneCheckbox.type = "checkbox";
            doneCheckbox.addEventListener("change", function () {
              // Update the task status when the checkbox is checked/unchecked
              task.status = doneCheckbox.checked ? "Done" : "Not Done";
              // Update the task in the database
              // fetch(API_URL + "/tasks/update", {
              //   method: "PUT",
              //   headers: {
              //     "Content-Type": "application/json",
              //   },
              //   body: JSON.stringify(task),
              // })
              //   .then((response) => response.json())
              //   .then((data) => {
              //     console.log("Success:", data);
              //   })
              //   .catch((error) => {
              //     console.error("Error:", error);
              //   });
            });
            doneCell.appendChild(doneCheckbox);
            row.appendChild(doneCell);

            tableBody.appendChild(row);
          });
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }
}

// Set up an interval to update the table every 5 seconds (adjust as needed)
const updateInterval = 5000; // 5 seconds
setInterval(updateTaskTable, updateInterval);

// Initial call to fetch and display tasks
