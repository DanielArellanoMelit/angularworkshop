import { IEmpleado, NewEmpleado } from './empleado.model';

export const sampleWithRequiredData: IEmpleado = {
  id: 19124,
};

export const sampleWithPartialData: IEmpleado = {
  id: 86345,
  nombre: 'Bicicleta',
  activo: true,
  numeroVentas: 55353,
};

export const sampleWithFullData: IEmpleado = {
  id: 69746,
  dni: 'incremental',
  nombre: 'óptima',
  activo: true,
  numeroVentas: 22533,
};

export const sampleWithNewData: NewEmpleado = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
