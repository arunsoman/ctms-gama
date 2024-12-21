import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskScore } from '../../../models/risk.types';

@Component({
  selector: 'app-risk-trend-chart',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="risk-trend-chart">
      <h3>Risk Score Trends</h3>
      <div class="chart-container">
        <div class="trend-bar" 
             *ngFor="let score of riskScores" 
             [style.height.%]="score.score * 100"
             [class]="getRiskLevel(score.score)">
          <span class="score-label">{{(score.score * 100).toFixed(0)}}%</span>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .risk-trend-chart {
      padding: 15px;
    }
    .chart-container {
      height: 200px;
      display: flex;
      align-items: flex-end;
      gap: 8px;
      padding: 10px 0;
    }
    .trend-bar {
      flex: 1;
      min-width: 30px;
      background: #e0e0e0;
      position: relative;
      transition: height 0.3s ease;
    }
    .trend-bar.high { background: #ff4444; }
    .trend-bar.medium { background: #ffbb33; }
    .trend-bar.low { background: #00C851; }
    .score-label {
      position: absolute;
      top: -20px;
      left: 50%;
      transform: translateX(-50%);
      font-size: 12px;
    }
  `]
})
export class RiskTrendChartComponent {
  @Input() riskScores: RiskScore[] = [];

  getRiskLevel(score: number): string {
    if (score >= 0.7) return 'high';
    if (score >= 0.4) return 'medium';
    return 'low';
  }
}