import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GraphConfigService {
  readonly nodeRadius = 20;
  readonly nodePadding = 50;
  readonly nodeColors = {
    account: '#4CAF50',
    individual: '#2196F3',
    business: '#9C27B0',
    device: '#FF9800',
    location: '#795548',
    ip: '#607D8B'
  };
  
  readonly riskColorScale = {
    high: '#f44336',
    medium: '#ff9800',
    low: '#4caf50'
  };

  getRiskColor(riskScore: number): string {
    if (riskScore >= 0.7) return this.riskColorScale.high;
    if (riskScore >= 0.4) return this.riskColorScale.medium;
    return this.riskColorScale.low;
  }

  getNodeColor(type: string): string {
    return this.nodeColors[type as keyof typeof this.nodeColors] || '#999';
  }
}