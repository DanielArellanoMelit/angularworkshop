export interface ICoche {
  id: number;
  marca?: string | null;
  modelo?: string | null;
  color?: string | null;
  numeroSerie?: string | null;
  precio?: number | null;
  exposicion?: boolean | null;
}

export type NewCoche = Omit<ICoche, 'id'> & { id: null };
