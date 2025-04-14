export interface Team {
  id: number;
  name: string;
}

export interface Match {
  team1: Team;
  team2: Team;
  team1Score: number;
  team2Score: number;
  played: boolean;
}
