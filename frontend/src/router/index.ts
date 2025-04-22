import { createRouter, createWebHistory } from "vue-router";
import LandingPage from "../views/LandingPage.vue";
import CreateTournament from "@/views/CreateTournament.vue";
import ResumeTournament from "@/views/ResumeTournament.vue";
import InGame from "@/views/InGame.vue";

const routes = [
  {
    path: "/",
    name: "landingPage",
    component: LandingPage,
  },
  {
    path: "/create",
    name: "create",
    component: CreateTournament,
  },
  {
    path: "/resume",
    name: "resume",
    component: ResumeTournament,
  },
  {
    path: "/ingame",
    name: "ingame",
    component: InGame,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
