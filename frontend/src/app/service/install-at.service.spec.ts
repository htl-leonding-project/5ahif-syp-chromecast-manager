import { TestBed } from '@angular/core/testing';

import { InstallAtService } from './install-at.service';

describe('InstallAtService', () => {
  let service: InstallAtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InstallAtService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
