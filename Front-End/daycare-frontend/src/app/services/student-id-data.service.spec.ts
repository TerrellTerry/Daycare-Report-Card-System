import { TestBed } from '@angular/core/testing';

import { StudentIdDataService } from './student-id-data.service';

describe('StudentIdDataService', () => {
  let service: StudentIdDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentIdDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
