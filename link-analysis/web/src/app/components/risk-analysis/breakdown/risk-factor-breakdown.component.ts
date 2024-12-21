import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskScore, RiskFactor } from '../../../models/risk.types';

@Component({
  selector: 'app-risk-factor-breakdown',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="risk-breakdown">
      <h3>Risk Factor Breakdown</h3>
      <div class="factors-grid">
        <div *ngFor="let factor of riskFactors" class="factor-card">
          <h4>{{factor.name}}</h4>
          <div class="progress-bar">
            <div class="progress" 
                 [style.width.%]="getFactorScore(factor.id) * 100"
                 [class]="getRiskLevel(getFactorScore(factor.id))">
              {{(getFactorScore(factor.id) * 100).toFixed(0)}}%
            </div>
          </div>
          <p class="description">{{factor.description}}</p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .risk-breakdown {
      padding: 15px;
    }
    .factors-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 15px;
      margin-top: 15px;
    }
    .factor-card {
      padding: 15px;
      background: #f8f9fa;
      border-radius: 8px;
    }
    .progress-bar {
      height: 24px;
      background: #e0e0e0;
      border-radius: 12px;
      margin: 10px 0;
      overflow: hidden;
    }
    .progress {
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      transition: width 0.3s ease;
    }
    .progress.high { background: #ff4444; }
    .progress.medium { background: #ffbb33; }
    .progress.low { background: #00C851; }
    .description {
      font-size: 0.9em;
      color: #666;
    }
  `]
})
export class RiskFactorBreakdownComponent {
  @Input() riskScore: RiskScore | null = null;
  @Input() riskFactors: RiskFactor[] = [];

  getFactorScore(factorId: string): number {
    return this.riskScore?.factors[factorId] || 0;
  }

  getRiskLevel(score: number): string {
    if (score >= 0.7) return 'high';
    if (score >= 0.4) return 'medium';
    return 'low';
  }
}