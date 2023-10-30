import { ICliente, NewCliente } from './cliente.model';

export const sampleWithRequiredData: ICliente = {
  id: 45820,
};

export const sampleWithPartialData: ICliente = {
  id: 31827,
  nombre: 'Travesía',
  numeroCompras: 37420,
  tier: 48105,
};

export const sampleWithFullData: ICliente = {
  id: 10736,
  dni: 'portals robust',
  nombre: 'Decoración Platinum',
  numeroCompras: 9907,
  tier: 17092,
};

export const sampleWithNewData: NewCliente = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
