<template>
  <div class="view-wrapper">
    <div class="card">
      <h1 class="heading">{{ tournamentName }}</h1>

      <div class="button-row">
        <button class="action-button" @click="playRound" :disabled="isPlayingRound">
          Play round
        </button>

        <button class="action-button" @click="updateRound" :disabled="!isPlayingRound">
          Update round
        </button>
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

    <!-- En match i taget visas för uppdatering -->
    <UpdateMatchForm v-if="showUpdateForm && currentIndex < matches.length" :match="matches[currentIndex]"
      @save="handleSave" />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { Match } from "@/models/types"
import Scoreboard from "../components/Scoreboard.vue"
import UpdateMatchForm from "../components/UpdateMatchForm.vue"

const tournamentName = ref("Padel Tournament");

const fetchTournamentName = async () => {
  try {
    const res = await fetch("http://localhost:8080/api/tournaments/get-name");
    if (res.ok) {
      tournamentName.value = await res.text();
    }
  } catch (err) {
    console.error("Failed to fetch tournament name", err);
  }
};

onMounted(fetchTournamentName);

const scoreboardRef = ref<InstanceType<typeof Scoreboard> | null>(null)
const matches = ref<Match[]>([])
const showUpdateForm = ref(false)
const currentIndex = ref(0)
const firstRoundPlayed = ref(false)
const isPlayingRound = ref(false)

const playRound = async () => {
  if (isPlayingRound.value) return;

  firstRoundPlayed.value = true;
  isPlayingRound.value = true;

  const res = await fetch("http://localhost:8080/api/tournaments/get-nextmatches")
  if (!res.ok) {
    console.error("Något gick fel vid hämtning")
    return
  }
  matches.value = await res.json()
  console.log("matcher hämtade: ", matches.value)
}

const updateRound = () => {
  if (!isPlayingRound.value) return;

  isPlayingRound.value = false;

  if (matches.value.length === 0) {
    alert("Inga matcher att uppdatera")
    return
  }
  currentIndex.value = 0
  showUpdateForm.value = true
}

const handleSave = (updatedMatch: Match) => {
  matches.value[currentIndex.value] = updatedMatch
  currentIndex.value++

  if (currentIndex.value >= matches.value.length) {
    showUpdateForm.value = false
    submitAllUpdates()
  }
}

const submitAllUpdates = async () => {
  const res = await fetch("http://localhost:8080/api/tournaments/update-matches", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(matches.value)
  })
  console.log("matcher skickade: ", matches.value)

  if (!res.ok) {
    alert("Något gick fel vid uppdatering.")
    return
  }

  if (scoreboardRef.value) {
    scoreboardRef.value.fetchStanding()
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

.action-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  opacity: 0.6;
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
</style>
