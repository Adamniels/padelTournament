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
        <ul class="list">
          <!--  TODO : make dynamic, should be a list -->
          <li>Team 1 vs Team 2</li>
          <li>Team 3 vs Team 4</li>
        </ul>
      </div>

      <div class="section scoreboard">
        <h2 class="subheading">Scoreboard</h2>
        <ol class="list">
          <!--  TODO : make dynamic, should be a list -->
          <li>Team 1 <span class="points">10p</span></li>
          <li>Team 2 <span class="points">7p</span></li>
          <li>Team 3 <span class="points">3p</span></li>
          <li>Team 4 <span class="points">0p</span></li>
        </ol>
      </div>
    </div>
  </div>
</template>


<script setup lang="ts">
// TODO: onMounted and every change should retrieve a status structs that is neccessery for displaying data

const playRound = () => {
  console.log("playing new round")
}

const updateRound = () => {
  console.log("updating round")
  // TODO: should get a new component above that lets the user write the result
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
