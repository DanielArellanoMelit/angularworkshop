import dayjs from 'dayjs/esm';
import { ICoche } from 'app/entities/coche/coche.model';
import { IEmpleado } from 'app/entities/empleado/empleado.model';
import { ICliente } from 'app/entities/cliente/cliente.model';

export interface IVenta {
  id: number;
  fecha?: dayjs.Dayjs | null;
  tipoPago?: string | null;
  total?: number | null;
  coches?: Pick<ICoche, 'id'> | null;
  empleado?: Pick<IEmpleado, 'id'> | null;
  cliente?: Pick<ICliente, 'id'> | null;
}

export type NewVenta = Omit<IVenta, 'id'> & { id: null };
