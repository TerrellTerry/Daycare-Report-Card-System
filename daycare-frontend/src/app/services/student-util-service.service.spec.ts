import { TestBed } from '@angular/core/testing';

import { StudentUtilServiceService } from './student-util-service.service';

describe('StudentUtilServiceService', () => {
  let service: StudentUtilServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentUtilServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
