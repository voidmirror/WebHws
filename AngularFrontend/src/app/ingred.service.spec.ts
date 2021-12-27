import { TestBed } from '@angular/core/testing';

import { IngredService } from './ingred.service';

describe('IngredService', () => {
  let service: IngredService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IngredService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
