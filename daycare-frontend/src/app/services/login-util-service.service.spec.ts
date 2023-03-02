import { TestBed } from '@angular/core/testing';

import { LoginUtilServiceService } from './login-util-service.service';

describe('LoginUtilServiceService', () => {
  let service: LoginUtilServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginUtilServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
