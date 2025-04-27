<template>
  <div class="home">
    <h1>Welcome To The Padel Tournament Generator!</h1>

    <RegisterTeamForm @team-added="refreshTeams" />

    <hr />

    <TeamList ref="teamListRef" />

    <form @submit.prevent="startTournament" class="tournament-form">
      <input v-model="tournamentName" placeholder="Turneringsnamn" required />

      <input v-model.number="courts" type="number" min="1" placeholder="Antal banor" required />

      <button class="start-button" type="submit">Start tournament</button>
    </form>

    <p v-if="feedback" class="feedback">{{ feedback }}</p>
  </div>
</template>

<script setup lang="ts">
import router from "@/router";
import RegisterTeamForm from "../components/RegisterTeamForm.vue";
import TeamList from "../components/TeamList.vue";
import { ref } from "vue";

const teamListRef = ref<InstanceType<typeof TeamList> | null>(null);
const tournamentName = ref("");
const feedback = ref("");
const courts = ref("");

const refreshTeams = () => {
  if (teamListRef.value) {
    teamListRef.value.fetchTeams();
  }
};

const startTournament = async () => {
  const res = await fetch("http://localhost:8080/api/tournaments/start", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name: tournamentName.value, courts: courts.value }),
  });

  if (res.ok) {
    feedback.value = "Turnering skapad!";
    tournamentName.value = "";
  } else {
    feedback.value = "Något gick fel vid skapandet.";
  }
  //move on to the ingame view
  router.push("/ingame")
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

.tournament-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  margin-top: 2rem;
}

.tournament-form input {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  width: 100%;
  max-width: 300px;
}

.start-button {
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

.feedback {
  text-align: center;
  margin-top: 1rem;
  color: var(--primary-color);
}
</style>
