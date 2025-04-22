<template>
  <div class="p-6">
    <h2 class="text-2xl mb-4 font-semibold">Välj en sparad turnering</h2>

    <ul class="space-y-2">
      <li v-for="t in tournaments" :key="t.id">
        <button @click="loadTournament(t.id)" class="w-full text-left px-4 py-2 border rounded hover:bg-gray-100">
          {{ t.name }} - {{ formatDate(t.createdAt) }}
        </button>
      </li>
    </ul>
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
  return value.split('T')[0]; // tar bara "YYYY-MM-DD" före 'T'
};




onMounted(fetchTournaments)
</script>
