<template>
  <div class="team-list">
    <h3>Teams</h3>

    <ul v-if="teams.length">
      <li v-for="(team, index) in teams" :key="index">
        {{ team.name }}
        <button @click="removeTeam(index)">X</button>
      </li>
    </ul>

    <p v-else>No team registered.</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const teams = ref([]);

const fetchTeams = async () => {
  try {
    const response = await fetch(
      "http://localhost:8080/api/tournaments/get-teams"
    );
    if (response.ok) {
      teams.value = await response.json();
    }
  } catch (error) {
    console.error("Couldn't get teams:", error);
  }
};

const removeTeam = async (index) => {
  const res = await fetch("http://localhost:8080/api/tournaments/remove-team", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(index),
  });

  if (res.ok) {
    teams.value.splice(index, 1); // Ta bort från local lista också
  } else {
    alert("Couldn't remove team.");
  }
};

onMounted(fetchTeams);

// Exportera funktionen så andra komponenter (t.ex. RegisterTeamForm) kan anropa den
defineExpose({ fetchTeams });
</script>

<style scoped>
.team-list {
  background: #fefefe;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
}

h3 {
  text-align: center;
  color: var(--primary-color);
  margin-bottom: 1rem;
  font-size: 1.5rem;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  background-color: #f3f3f3;
  margin: 0.5rem 0;
  padding: 0.75rem 1rem;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.03);
  font-weight: 500;
  color: #333;
}

p {
  text-align: center;
  color: #888;
  font-style: italic;
  margin-top: 1rem;
}
</style>
