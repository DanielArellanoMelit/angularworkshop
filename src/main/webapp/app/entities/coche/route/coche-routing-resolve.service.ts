import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICoche } from '../coche.model';
import { CocheService } from '../service/coche.service';

@Injectable({ providedIn: 'root' })
export class CocheRoutingResolveService implements Resolve<ICoche | null> {
  constructor(protected service: CocheService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICoche | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((coche: HttpResponse<ICoche>) => {
          if (coche.body) {
            return of(coche.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
