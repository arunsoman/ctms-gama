import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { NetworkData } from '../models/types';
import { MockDataService } from './mock-data.service';

@Injectable({
  providedIn: 'root'
})
export class NetworkDataService {
  private networkDataSubject = new BehaviorSubject<NetworkData | null>(null);
  readonly networkData$ = this.networkDataSubject.asObservable();

  constructor(private mockDataService: MockDataService) {
    this.loadInitialData();
  }

  private loadInitialData() {
    this.mockDataService.getNetworkData().subscribe(data => {
      this.networkDataSubject.next(data);
    });
  }

  getCurrentData(): NetworkData | null {
    return this.networkDataSubject.value;
  }

  updateNetworkData(data: NetworkData) {
    this.networkDataSubject.next(data);
  }
}