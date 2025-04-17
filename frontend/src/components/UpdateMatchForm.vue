<template>
  <div class="overlay">
    <div class="form-card" v-if="match">
      <h2>Uppdatera match</h2>

      <p><strong>{{ match.team1.name }}</strong> vs <strong>{{ match.team2.name }}</strong></p>

      <label>Poäng för {{ match.team1.name }}</label>
      <input v-model.number="scoreA" type="number" />

      <label>Poäng för {{ match.team2.name }}</label>
      <input v-model.number="scoreB" type="number" />

      <button @click="submit">Spara</button>
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
}
</style>
