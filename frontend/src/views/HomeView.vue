<template>
    <div class="home">
      <h1>Welcome To The Padel Tournament Generator!</h1>
      <RegisterTeamForm @team-added="refreshTeams" />
  
      <hr />
  
      <TeamList ref="teamListRef" />
  
      <button class="start-button" @click="startTournament">
        Start tournament
      </button>
    </div>
  </template>

<script setup lang="ts">
import RegisterTeamForm from "../components/RegisterTeamForm.vue";
import TeamList from "../components/TeamList.vue";
import { ref } from "vue";

const teamListRef = ref(null);

const refreshTeams = () => {
  if (teamListRef.value) {
    teamListRef.value.fetchTeams();
  }
};

const startTournament = () => {
  alert("Turneringen är igång!");
  // Här kan du t.ex. navigera till nästa vy eller anropa backend sen
};
</script>

<style scoped>
.home {
  max-width: 800px;
  margin: 2rem auto;
  background-color: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  font-family: var(--font-stack);
}

h1 {
  text-align: center;
  font-size: 2.2rem;
  margin-bottom: 2rem;
  color: var(--primary-color);
}

hr {
  margin: 2rem 0;
  border: none;
  height: 1px;
  background-color: #ddd;
}

.start-button {
  display: block;
  margin: 2rem auto 0;
  padding: 0.75rem 1.5rem;
  font-size: 1rem;
  background-color: var(--primary-color);
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.start-button:hover {
  background-color: #2c9c6a;
}
</style>