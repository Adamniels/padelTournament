<template>
  <div class="team-list">
    <h3>Scoreboard</h3>

    <ul v-if="scoreboard.length">
      <li class="header">
        <span>Team</span>
        <span>Played</span>
        <span>Won</span>
        <span>Score</span>
      </li>
      <li v-for="(team, index) in scoreboard" :key="team.id">
        <span>{{ team.name }}</span>
        <span>{{ team.playedMatches }}</span>
        <span>{{ team.wonMatches }}</span>
        <span class="points">{{ team.score }}</span>
      </li>
    </ul>

    <p v-else>No team registered.</p>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import type { Team } from "@/models/types"

const scoreboard = ref<Team[]>([]);

const fetchStanding = async () => {
  try {
    const response = await fetch(
      "http://localhost:8080/api/tournaments/get-standing"
    );
    if (response.ok) {
      scoreboard.value = await response.json();
    }
  } catch (error) {
    console.error("Couldn't get teams:", error);
  }
};

onMounted(fetchStanding);

defineExpose({ fetchStanding });
</script>

<style scoped>
.team-list {
  width: 100%;
  margin-top: 2rem;
}

h3 {
  color: var(--primary-color);
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

li {
  background-color: #f2f2f2;
  padding: 0.5rem 1rem;
  border-radius: 6px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1rem;
  font-weight: 500;
}

li.header {
  background-color: #d6e9dc;
  font-weight: bold;
}

li span {
  width: 25%;
  text-align: center;
}

.points {
  font-weight: bold;
}
</style>
