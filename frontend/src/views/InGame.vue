<template>
  <div class="view-wrapper">
    <div class="card">
      <h1 class="heading">Namn på turnering</h1>

      <div class="button-row">
        <button class="action-button" @click="playRound">Play round</button>
        <button class="action-button" @click="updateRound">Update round</button>
      </div>

      <div class="section">
        <h2 class="subheading">Current matches</h2>
        <div v-if="matches.length > 0">
          <ul class="list">
            <li v-for="(match, index) in matches" :key="index">
              {{ match.team1.name }} vs {{ match.team2.name }}
            </li>
          </ul>
        </div>
        <div v-else>
          <p v-if="firstRoundPlayed">Tournament over</p>
          <p v-else>Press play round to start</p>
        </div>
      </div>

      <Scoreboard ref="scoreboardRef" />

    </div>
  </div>
</template>


<script setup lang="ts">
// TODO: onMounted and every change should retrieve a status structs that is neccessery for displaying data
// scoreboard handles this but need to do a similiar solution like teamlist so it updates after every update

import { ref } from 'vue'
import type { Match } from "@/models/types"; // använd rätt path beroende på var din ts-fil ligger
import Scoreboard from "../components/Scoreboard.vue";

const scoreboardRef = ref<InstanceType<typeof Scoreboard> | null>(null);

let firstRoundPlayed = false;
const matches = ref<Match[]>([]);

const playRound = async () => {
  firstRoundPlayed = true;
  const res = await fetch("http://localhost:8080/api/tournaments/get-nextmatches");
  if (!res.ok) {
    console.error("Något gick fel vid hämtning");
    return;
  }
  matches.value = await res.json();

}


const updateRound = () => {
  console.log("updating round")
  // TODO: should get a new component above that lets the user write the result
  if (scoreboardRef.value) {
    scoreboardRef.value.fetchStanding();
  }
}

</script>

<style scoped>
.view-wrapper {
  display: flex;
  justify-content: center;
  padding: 2rem;
}

.card {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
  max-width: 600px;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.heading {
  font-size: 1.8rem;
  color: #2ecc71;
  margin-bottom: 1.5rem;
  text-align: center;
}

.subheading {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
  color: #2ecc71;
}

.button-row {
  display: flex;
  gap: 1rem;
  margin-bottom: 2rem;
  flex-wrap: wrap;
  justify-content: center;
}

.action-button {
  padding: 0.5rem 1.5rem;
  background-color: #2ecc71;
  color: white;
  border: none;
  border-radius: 999px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.2s ease;
}

.action-button:hover {
  background-color: #27ae60;
}

.section {
  width: 100%;
  margin-top: 1rem;
}

.list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.list li {
  background: #f2f2f2;
  padding: 0.5rem 1rem;
  margin-bottom: 0.5rem;
  border-radius: 6px;
  display: flex;
  justify-content: space-between;
}

.points {
  font-weight: bold;
}
</style>
