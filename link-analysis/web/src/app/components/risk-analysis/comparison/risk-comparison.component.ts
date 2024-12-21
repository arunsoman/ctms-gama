import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Entity } from '../../../models/types';

@Component({
  selector: 'app-risk-comparison',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="comparison-container">
      <h3>Risk Score Comparison</h3>
      <div class="comparison-chart">
        <div class="entity-score" *ngFor="let entity of sortedEntities">
          <div class="score-info">
            <span class="score-value">{{(entity.riskScore * 100).toFixed(0)}}%</span>
            <span class="entity-name">{{entity.name}}</span>
          </div>
          <div class="score-bar-container">
            <div class="score-bar" 
                 [style.width.%]="entity.riskScore * 100"
                 [class]="getRiskLevel(entity.riskScore)">
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .comparison-container {
      padding: 20px;
    }

    h3 {
      margin-bottom: 20px;
      color: #333;
      font-size: 1.2em;
    }

    .comparison-chart {
      display: flex;
      flex-direction: column;
      gap: 12px;
    }

    .entity-score {
      display: flex;
      align-items: center;
      gap: 15px;
    }

    .score-info {
      width: 180px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .score-value {
      font-weight: 600;
      font-size: 1.1em;
      width: 50px;
    }

    .entity-name {
      color: #444;
      font-size: 0.95em;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .score-bar-container {
      flex: 1;
      height: 24px;
      background: #f0f0f0;
      border-radius: 12px;
      overflow: hidden;
    }

    .score-bar {
      height: 100%;
      transition: width 0.3s ease;
      border-radius: 12px;
    }

    .score-bar.high { 
      background: linear-gradient(to right, #ff4444, #ff6666);
    }

    .score-bar.medium { 
      background: linear-gradient(to right, #ffbb33, #ffcc66);
    }

    .score-bar.low { 
      background: linear-gradient(to right, #00C851, #00E676);
    }
  `]
})
export class RiskComparisonComponent {
  @Input() entities: Entity[] = [];

  get sortedEntities() {
    return [...this.entities].sort((a, b) => b.riskScore - a.riskScore);
  }

  getRiskLevel(score: number): string {
    if (score >= 0.7) return 'high';
    if (score >= 0.4) return 'medium';
    return 'low';
  }
}