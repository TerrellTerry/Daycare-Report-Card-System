import { TestBed } from '@angular/core/testing';

import { GradeUtilServiceService } from './grade-util-service.service';

describe('GradeUtilServiceService', () => {
  let service: GradeUtilServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GradeUtilServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
