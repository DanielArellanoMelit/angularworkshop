import dayjs from 'dayjs/esm';

import { IVenta, NewVenta } from './venta.model';

export const sampleWithRequiredData: IVenta = {
  id: 20448,
};

export const sampleWithPartialData: IVenta = {
  id: 11474,
  tipoPago: 'Rioja',
};

export const sampleWithFullData: IVenta = {
  id: 41728,
  fecha: dayjs('2023-10-30'),
  tipoPago: 'Funcionalidad',
  total: 29440,
};

export const sampleWithNewData: NewVenta = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
