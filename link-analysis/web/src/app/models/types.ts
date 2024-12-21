export interface Entity {
  id: string;
  type: 'account' | 'individual' | 'business' | 'device' | 'location' | 'ip';
  name: string;
  riskScore: number;
  flags?: string[];
}

export interface Transaction {
  id: string;
  sourceId: string;
  targetId: string;
  amount: number;
  timestamp: Date;
  riskScore: number;
}

export interface NetworkData {
  entities: Entity[];
  transactions: Transaction[];
}

export interface FilterCriteria {
  dateRange: {
    start: Date | null;
    end: Date | null;
  };
  riskThreshold: number;
  entityTypes: string[];
}

export interface CircularTransfer {
  path: string[];
  totalAmount: number;
  riskScore: number;
}