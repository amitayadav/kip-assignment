import { TestBed, inject } from '@angular/core/testing';

import { OrderingServiceService } from './ordering-service.service';

describe('OrderingServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OrderingServiceService]
    });
  });

  it('should be created', inject([OrderingServiceService], (service: OrderingServiceService) => {
    expect(service).toBeTruthy();
  }));
});
