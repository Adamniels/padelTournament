<template>
    <div class="register-team">
      <h3>Register a new team</h3>
      <form @submit.prevent="submitTeam">
        <input v-model="teamName" placeholder="Lagnamn" />
        <button type="submit">Add team</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  
  const emit = defineEmits(["team-added"]);
  
  const teamName = ref("");
  
  const submitTeam = async () => {
    if (!teamName.value.trim()) return;
  
    const response = await fetch("http://localhost:8080/api/teams", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: teamName.value }),
    });
  
    if (response.ok) {
      alert("Lag registrerat!");
      teamName.value = "";
      emit("team-added");
    } else {
      alert("Något gick fel!");
    }
  };
  </script>
  
  <style scoped>
  .register-team {
    background: #fefefe;
    padding: 1.5rem;
    border-radius: 12px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
    margin-bottom: 2rem;
  }
  
  h3 {
    text-align: center;
    color: var(--primary-color);
    margin-bottom: 1rem;
    font-size: 1.5rem;
  }
  
  form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }
  
  input {
    padding: 0.75rem 1rem;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1rem;
    transition: border 0.2s;
  }
  
  input:focus {
    outline: none;
    border-color: var(--primary-color);
  }
  
  button {
    align-self: center;
    padding: 0.75rem 1.5rem;
    font-size: 1rem;
    background-color: var(--primary-color);
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.2s;
  }
  
  button:hover {
    background-color: #2c9c6a;
  }
  </style>
  
