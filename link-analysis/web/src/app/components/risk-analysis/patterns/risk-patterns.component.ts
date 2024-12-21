import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskPattern } from '../../../models/risk.types';

@Component({
  selector: 'app-risk-patterns',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="risk-patterns">
      <h3>Detected Risk Patterns</h3>
      <div class="patterns-grid">
        <div *ngFor="let pattern of patterns" 
             class="pattern-card"
             [class]="pattern.severity">
          <h4>{{pattern.name}}</h4>
          <p class="description">{{pattern.description}}</p>
          <div class="indicators">
            <span *ngFor="let indicator of pattern.indicators" 
                  class="indicator">{{indicator}}</span>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .risk-patterns {
      padding: 15px;
    }
    .patterns-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 15px;
      margin-top: 15px;
    }
    .pattern-card {
      padding: 15px;
      border-radius: 8px;
      border-left: 4px solid #999;
    }
    .pattern-card.high { 
      border-left-color: #ff4444;
      background: #ffebee;
    }
    .pattern-card.medium { 
      border-left-color: #ffbb33;
      background: #fff3e0;
    }
    .pattern-card.low { 
      border-left-color: #00C851;
      background: #e8f5e9;
    }
    .indicators {
      display: flex;
      flex-wrap: wrap;
      gap: 8px;
      margin-top: 10px;
    }
    .indicator {
      background: rgba(255,255,255,0.5);
      padding: 4px 8px;
      border-radius: 12px;
      font-size: 0.9em;
    }
  `]
})
export class RiskPatternsComponent {
  @Input() patterns: RiskPattern[] = [];
}