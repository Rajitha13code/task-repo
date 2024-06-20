<template>
  <div class="task-app">
    <h2>Task Registration</h2>
    <form @submit.prevent="save">
      <div class="form-group">
        <label>Task Title</label>
        <input
          type="text"
          v-model="task.taskTitle"
          class="form-control"
          placeholder="Task Title"/>
      </div>
      <div class="form-group">
        <label>Task Description</label>
        <input
          type="text"
          v-model="task.taskDescription"
          class="form-control"
          placeholder="Task Description"/>
      </div>
      <div class="form-group">
        <label>Due Date</label>
        <input
          type="date"
          v-model="task.dueDate"
          class="form-control"
          placeholder="Due Date"/>
      </div>
      <button type="submit" class="btn btn-primary">Save</button>
    </form>
    <h2>Task View</h2>
    <table class="table  table-bordered table-dark">
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Task Title</th>
          <th scope="col">Task Description</th>
          <th scope="col">Due Date</th>
          <th scope="col">Option</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="task in tasks" :key="task.taskId">
          <td>{{ task.taskId }}</td>
          <td>{{ task.taskTitle }}</td>
          <td>{{ task.taskDescription }}</td>
          <td>{{ task.dueDate }}</td>
          <td>
            <button type="button" class="btn btn-warning" @click="edit(task)">
              Edit
            </button>
            <button
              type="button"
              class="btn btn-danger"
              @click="remove(task.taskId)"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
// eslint-disable-next-line
/* eslint-disable */
import axios from 'axios';
export default {
  name: 'Task',
  data() {
    return {
      tasks: [],
      task: {
        taskId: '',
        taskTitle: '',
        taskDescription: '',
        dueDate: '',
      },
    };
  },
  created() {
    this.loadTasks();
  },
  methods: {
    loadTasks() {
      axios
        .get('http://localhost:8000/api/task/getAllTasks')
        .then((response) => {
          this.tasks = response.data;
        })
        .catch((error) => {
          console.error('There was an error loading the tasks:', error);
        });
    },
    save() {
      if (this.task.taskId) {
        this.updateTask();
      } else {
        this.addTask();
      }
    },
    addTask() {
      axios
        .post('http://localhost:8000/api/task/save', this.task)
        .then((response) => {
          //alert('Task saved successfully');
            const errorMessage = response.data.split(',')[1]; // Extract error message
            if(errorMessage)
            alert(errorMessage.trim()); // Show error message
            this.resetForm();
            this.loadTasks();
        }
        
        )
        .catch((error) => {
          console.error('There was an error saving the task:', error);
        });
    },
    edit(task) {
      this.task = { ...task };
    },
    updateTask() {
      axios
        .put('http://localhost:8000/api/task/updateTask', this.task)
        .then(() => {
          alert('Task updated successfully');
          this.resetForm(),
          this.loadTasks();
        })
        .catch((error) => {
          console.error('There was an error updating the task:', error);
        });
    },
    remove(taskId) {
      axios
        .delete(`http://localhost:8000/api/task/deleteTask/${taskId}`)
        .then(() => {
          alert('Task deleted successfully');
          this.loadTasks();
        })
        .catch((error) => {
          console.error('There was an error deleting the task:', error);
        });
    },
    resetForm() {
      this.task = {
        taskId: '',
        taskTitle: '',
        taskDescription: '',
        dueDate: '',
      };
    },
  },
};
</script>
<style>
.task-app {
  font-family: Arial, sans-serif;
  text-align: center;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

.btn {
  margin-right: 10px;
}

.table {
  background-color: white;
}

.table-bordered {
  border: 1px solid white;
}

.table-bordered th,
.table-bordered td {
  border: 1px solid white;
}

.table-dark {
  background-color: grey;
  color: white;
}

.table-dark th,
.table-dark td,
.table-dark thead th {
  border-color: #454d55;
}
</style>
