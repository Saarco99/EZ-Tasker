document.querySelector("form").addEventListener("submit", function (event) {
  // Prevent the form from being submitted
  event.preventDefault();

  // Get the task name and description
  var taskName = document.getElementById("taskName").value;
  var taskDescription = document.getElementById("taskDescription").value;

  // Create a new list item and add a class to it
  var li = document.createElement("li");
  li.className = "task-item";

  // Create the task name and description elements
  var taskNameElement = document.createElement("h3");
  taskNameElement.textContent = taskName;
  var taskDescriptionElement = document.createElement("p");
  taskDescriptionElement.textContent = taskDescription;

  // Add the task name and description to the list item
  li.appendChild(taskNameElement);
  li.appendChild(taskDescriptionElement);

  // Add the list item to the task list
  document.getElementById("taskList").appendChild(li);

  // Clear the form
  document.getElementById("taskName").value = "";
  document.getElementById("taskDescription").value = "";
});
