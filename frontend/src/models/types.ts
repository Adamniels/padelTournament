export interface Team {
  id: number;
  name: string;
  score: number;
  wonMatches: number;
  playedMatches: number;
}

export interface Match {
  team1: Team;
  team2: Team;
  team1score: number;
  team2score: number;
  isPlayed: boolean;
}
