import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Entity, NetworkData, Transaction } from '../models/types';

@Injectable({
  providedIn: 'root'
})
export class MockDataService {
  private mockEntities: Entity[] = [
    {
      id: '1',
      type: 'account',
      name: 'Account A',
      riskScore: 0.8,
      flags: ['suspicious_activity', 'high_risk_jurisdiction', 'multiple_accounts']
    },
    {
      id: '2',
      type: 'individual',
      name: 'John Doe',
      riskScore: 0.3,
      flags: ['low_activity']
    },
    {
      id: '3',
      type: 'business',
      name: 'ABC Corp',
      riskScore: 0.6,
      flags: ['pep', 'offshore']
    },
    {
      id: '4',
      type: 'account',
      name: 'Account B',
      riskScore: 0.9,
      flags: ['high_volume', 'suspicious_pattern', 'sanctioned_country']
    },
    {
      id: '5',
      type: 'business',
      name: 'XYZ Ltd',
      riskScore: 0.4,
      flags: ['complex_ownership']
    },
    {
      id: '6',
      type: 'individual',
      name: 'Jane Smith',
      riskScore: 0.7,
      flags: ['multiple_accounts', 'rapid_transactions']
    }
  ];

  private mockTransactions: Transaction[] = [
    {
      id: 't1',
      sourceId: '1',
      targetId: '2',
      amount: 9500,
      timestamp: new Date(2024, 0, 1, 10, 0),
      riskScore: 0.7
    },
    {
      id: 't2',
      sourceId: '2',
      targetId: '3',
      amount: 9400,
      timestamp: new Date(2024, 0, 1, 10, 30),
      riskScore: 0.4
    },
    {
      id: 't3',
      sourceId: '3',
      targetId: '1',
      amount: 9450,
      timestamp: new Date(2024, 0, 1, 11, 0),
      riskScore: 0.8
    },
    {
      id: 't4',
      sourceId: '4',
      targetId: '5',
      amount: 8000,
      timestamp: new Date(2024, 0, 1, 12, 0),
      riskScore: 0.3
    },
    {
      id: 't5',
      sourceId: '5',
      targetId: '6',
      amount: 8500,
      timestamp: new Date(2024, 0, 1, 12, 30),
      riskScore: 0.6
    },
    {
      id: 't6',
      sourceId: '6',
      targetId: '4',
      amount: 8200,
      timestamp: new Date(2024, 0, 1, 13, 0),
      riskScore: 0.5
    },
    // Add more transactions to show rapid movement pattern
    {
      id: 't7',
      sourceId: '1',
      targetId: '4',
      amount: 15000,
      timestamp: new Date(2024, 0, 1, 14, 0),
      riskScore: 0.8
    },
    {
      id: 't8',
      sourceId: '4',
      targetId: '2',
      amount: 14500,
      timestamp: new Date(2024, 0, 1, 14, 15),
      riskScore: 0.7
    },
    {
      id: 't9',
      sourceId: '2',
      targetId: '5',
      amount: 14000,
      timestamp: new Date(2024, 0, 1, 14, 30),
      riskScore: 0.9
    }
  ];

  getNetworkData(): Observable<NetworkData> {
    return of({
      entities: this.mockEntities,
      transactions: this.mockTransactions
    });
  }
}