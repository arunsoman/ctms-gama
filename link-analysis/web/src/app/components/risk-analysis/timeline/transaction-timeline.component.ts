import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Transaction } from '../../../models/types';

@Component({
  selector: 'app-transaction-timeline',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="timeline-container">
      <h3>Transaction Timeline</h3>
      <div class="timeline">
        <div *ngFor="let tx of sortedTransactions" 
             class="timeline-item"
             [class]="getRiskLevel(tx.riskScore)">
          <div class="timeline-content">
            <span class="time">{{tx.timestamp | date:'short'}}</span>
            <span class="amount">{{tx.amount | currency}}</span>
            <span class="risk">Risk: {{(tx.riskScore * 100).toFixed(0)}}%</span>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .timeline-container {
      padding: 15px;
    }
    .timeline {
      display: flex;
      flex-direction: column;
      gap: 10px;
      margin-top: 15px;
    }
    .timeline-item {
      padding: 10px;
      border-radius: 6px;
      border-left: 4px solid #999;
    }
    .timeline-item.high { 
      border-left-color: #ff4444;
      background: #ffebee;
    }
    .timeline-item.medium { 
      border-left-color: #ffbb33;
      background: #fff3e0;
    }
    .timeline-item.low { 
      border-left-color: #00C851;
      background: #e8f5e9;
    }
    .timeline-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .time { color: #666; }
    .amount { font-weight: bold; }
    .risk { font-size: 0.9em; }
  `]
})
export class TransactionTimelineComponent {
  @Input() transactions: Transaction[] = [];

  get sortedTransactions() {
    return [...this.transactions].sort(
      (a, b) => new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime()
    );
  }

  getRiskLevel(score: number): string {
    if (score >= 0.7) return 'high';
    if (score >= 0.4) return 'medium';
    return 'low';
  }
}