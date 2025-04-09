<template>
  <div class="landing-container">
    <h1>Welcome To The Padel Tournament Generator!</h1>
    <div class="button-group">
      <button @click="goToCreate">Create a new tournament</button>
      <button @click="goToResume">Resume tournament</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();

async function goToCreate() {
  // här ska en turnering skapas också
  const res = await fetch("http://localhost:8080/api/tournaments", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({}), // tom body eller vad servern kräver
  });
  router.push("/create");
}

function goToResume() {
  router.push("/resume"); // byt till rätt route senare
}
</script>

<style scoped>
.landing-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  text-align: center;
  background-color: #f9f9f9;
}

h1 {
  font-size: 2rem;
  margin-bottom: 2rem;
}

.button-group {
  display: flex;
  gap: 1rem;
}

button {
  padding: 1rem 2rem;
  font-size: 1rem;
  cursor: pointer;
  background-color: #42b983;
  border: none;
  border-radius: 8px;
  color: white;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #2c9f75;
}
</style>
