import { ICoche, NewCoche } from './coche.model';

export const sampleWithRequiredData: ICoche = {
  id: 5829,
};

export const sampleWithPartialData: ICoche = {
  id: 95542,
  marca: 'redundant Account',
  color: 'Negro',
};

export const sampleWithFullData: ICoche = {
  id: 68493,
  marca: 'Unido Cine compress',
  modelo: 'Increible',
  color: 'Amarillo',
  numeroSerie: 'Enfocado',
  precio: 48182,
  exposicion: false,
};

export const sampleWithNewData: NewCoche = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
