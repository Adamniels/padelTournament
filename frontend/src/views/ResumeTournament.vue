<template>
  <div class="resume-tournament">
    <h1>Resume a Tournament</h1>

    <div class="card">
      <h2>Select a saved tournament</h2>

      <ul class="tournament-list">
        <li v-for="t in tournaments" :key="t.id">
          <button @click="loadTournament(t.id)" class="tournament-button">
            <span class="tournament-name">{{ t.name }}</span>
            <span class="tournament-date">{{ formatDate(t.createdAt) }}</span>
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const tournaments = ref([])

const fetchTournaments = async () => {
  const res = await fetch('http://localhost:8080/api/tournaments/all')
  tournaments.value = await res.json()
  console.log("Tournaments från backend:", tournaments.value)
}

const loadTournament = async (id: number) => {
  await fetch('http://localhost:8080/api/tournaments/load', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ id })
  })
  router.push('/ingame')
}

const formatDate = (value: string | undefined) => {
  if (!value || typeof value !== 'string') return 'Okänt datum';
  return value.split('T')[0];
}

onMounted(fetchTournaments)
</script>

<style scoped>
.resume-tournament {
  min-height: 100vh;
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 20px;
}

.resume-tournament h1 {
  font-size: 32px;
  font-weight: bold;
  color: #4CAF83;
  margin-bottom: 40px;
  text-align: center;
}

.card {
  background-color: white;
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.05);
  width: 100%;
  max-width: 500px;
  text-align: center;
}

.card h2 {
  font-size: 24px;
  color: #4CAF83;
  margin-bottom: 20px;
}

.tournament-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.tournament-list li {
  margin-bottom: 20px;
}

.tournament-button {
  width: 100%;
  padding: 12px;
  background-color: white;
  border: 2px solid #ccc;
  border-radius: 8px;
  color: #333;
  font-size: 16px;
  cursor: pointer;
  transition: border-color 0.3s, background-color 0.3s;
}

.tournament-button:hover {
  border-color: #4CAF83;
  background-color: #f7fdf9;
  /* väldigt ljus grön bakgrund vid hover */
}

.tournament-name {
  display: block;
  font-weight: bold;
  font-size: 18px;
}

.tournament-date {
  display: block;
  font-size: 14px;
  opacity: 0.8;
  margin-top: 4px;
}
</style>
