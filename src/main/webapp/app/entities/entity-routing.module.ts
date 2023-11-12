import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      /*{
        path: 'cliente',
        data: { pageTitle: 'Clientes' },
        loadChildren: () => import('./cliente/cliente.module').then(m => m.ClienteModule),
      },
      {
        path: 'venta',
        data: { pageTitle: 'Ventas' },
        loadChildren: () => import('./venta/venta.module').then(m => m.VentaModule),
      },
      {
        path: 'empleado',
        data: { pageTitle: 'Empleados' },
        loadChildren: () => import('./empleado/empleado.module').then(m => m.EmpleadoModule),
      },
      {
        path: 'coche',
        data: { pageTitle: 'Coches' },
        loadChildren: () => import('./coche/coche.module').then(m => m.CocheModule),
      },*/
    ]),
  ],
})
export class EntityRoutingModule {}
