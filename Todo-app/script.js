const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const pendingList = document.getElementById("pendingList");
const completedList = document.getElementById("completedList");

let tasks = JSON.parse(localStorage.getItem("tasks")) || [];

addBtn.addEventListener("click", addTask);

function saveTasks() {
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

function addTask() {
    const taskText = taskInput.value.trim();

    if (taskText === "") {
        alert("Please enter a task");
        return;
    }

    const task = {
        text: taskText,
        completed: false
    };

    tasks.push(task);
    saveTasks();
    displayTasks();

    taskInput.value = "";
}

function displayTasks() {
    pendingList.innerHTML = "";
    completedList.innerHTML = "";

    tasks.forEach((task, index) => {
        const li = document.createElement("li");

        const taskSpan = document.createElement("span");
        taskSpan.textContent = task.text;

        const editBtn = document.createElement("button");
        editBtn.textContent = "Edit";

        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";

        li.appendChild(taskSpan);

        if (!task.completed) {
            const completeBtn = document.createElement("button");
            completeBtn.textContent = "Complete";

            completeBtn.addEventListener("click", function () {
                tasks[index].completed = true;
                saveTasks();
                displayTasks();
            });

            li.appendChild(completeBtn);
            li.appendChild(editBtn);
            li.appendChild(deleteBtn);
            pendingList.appendChild(li);
        } else {
            li.classList.add("completed");
            li.appendChild(editBtn);
            li.appendChild(deleteBtn);
            completedList.appendChild(li);
        }

        editBtn.addEventListener("click", function () {
            const updatedTask = prompt("Edit Task:", task.text);

            if (updatedTask !== null && updatedTask.trim() !== "") {
                tasks[index].text = updatedTask.trim();
                saveTasks();
                displayTasks();
            }
        });

        deleteBtn.addEventListener("click", function () {
            tasks.splice(index, 1);
            saveTasks();
            displayTasks();
        });
    });
}

displayTasks();