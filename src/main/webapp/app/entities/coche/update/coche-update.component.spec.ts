import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CocheFormService } from './coche-form.service';
import { CocheService } from '../service/coche.service';
import { ICoche } from '../coche.model';

import { CocheUpdateComponent } from './coche-update.component';

describe('Coche Management Update Component', () => {
  let comp: CocheUpdateComponent;
  let fixture: ComponentFixture<CocheUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cocheFormService: CocheFormService;
  let cocheService: CocheService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CocheUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CocheUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CocheUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cocheFormService = TestBed.inject(CocheFormService);
    cocheService = TestBed.inject(CocheService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const coche: ICoche = { id: 456 };

      activatedRoute.data = of({ coche });
      comp.ngOnInit();

      expect(comp.coche).toEqual(coche);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICoche>>();
      const coche = { id: 123 };
      jest.spyOn(cocheFormService, 'getCoche').mockReturnValue(coche);
      jest.spyOn(cocheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ coche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: coche }));
      saveSubject.complete();

      // THEN
      expect(cocheFormService.getCoche).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cocheService.update).toHaveBeenCalledWith(expect.objectContaining(coche));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICoche>>();
      const coche = { id: 123 };
      jest.spyOn(cocheFormService, 'getCoche').mockReturnValue({ id: null });
      jest.spyOn(cocheService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ coche: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: coche }));
      saveSubject.complete();

      // THEN
      expect(cocheFormService.getCoche).toHaveBeenCalled();
      expect(cocheService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICoche>>();
      const coche = { id: 123 };
      jest.spyOn(cocheService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ coche });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cocheService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
