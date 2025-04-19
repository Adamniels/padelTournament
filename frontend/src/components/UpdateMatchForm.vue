<template>
  <div class="overlay">
    <div class="form-card" v-if="match">
      <h2>Update Match</h2>

      <p class="match-title">
        <strong>{{ match.team1.name }}</strong> vs <strong>{{ match.team2.name }}</strong>
      </p>

      <div class="score-row">
        <label>Points for {{ match.team1.name }}</label>
        <input v-model.number="scoreA" type="text" inputmode="numeric" pattern="[0-9]*" />
      </div>

      <div class="score-row">
        <label>Points for {{ match.team2.name }}</label>
        <input v-model.number="scoreB" type="text" inputmode="numeric" pattern="[0-9]*" />
      </div>

      <div class="button-wrapper">
        <button @click="submit">Save</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
  match: Object
})

const emit = defineEmits(['save'])

const scoreA = ref(0)
const scoreB = ref(0)

watch(() => props.match, (newMatch) => {
  if (newMatch) {
    scoreA.value = newMatch.scoreA || 0
    scoreB.value = newMatch.scoreB || 0
  }
}, { immediate: true })

const submit = () => {
  const updatedMatch = {
    ...props.match,
    team1score: scoreA.value,
    team2score: scoreB.value,
    isPlayed: true
  }

  emit('save', updatedMatch)
}
</script>

<style scoped>
.overlay {
  position: fixed;
  z-index: 100;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.7);
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-card {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  max-width: 400px;
  width: 90%;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.match-title {
  font-size: 1.1rem;
  margin-bottom: 1rem;
  text-align: center;
}

.score-row {
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
  margin-bottom: 1rem;
}

input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1rem;
  width: 100%;
  max-width: 150px;
  text-align: center;
  margin: 0 auto;
}

input:focus {
  outline: none;
  border-color: #2ecc71;
  box-shadow: 0 0 0 2px rgba(46, 204, 113, 0.3);
}

.button-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
}

button {
  background-color: #2ecc71;
  color: white;
  border: none;
  padding: 0.5rem 1.5rem;
  border-radius: 999px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.2s ease;
}

button:hover {
  background-color: #27ae60;
}
</style>
