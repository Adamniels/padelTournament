<script setup lang="ts">
import HomeView from "./views/HomeView.vue";
</script>

<template>
  <div class="app-wrapper">
    <router-view />
  </div>
</template>

<style>
.app-wrapper {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
}
</style>
