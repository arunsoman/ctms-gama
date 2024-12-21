import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Entity } from '../../../models/types';

@Component({
  selector: 'app-entities-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="entities-container">
      <h3>Entities</h3>
      <div class="entity-list">
        <div *ngFor="let entity of entities" 
             class="entity-card"
             [class.high-risk]="entity.riskScore >= 0.7"
             [class.medium-risk]="entity.riskScore >= 0.4 && entity.riskScore < 0.7"
             [class.low-risk]="entity.riskScore < 0.4">
          <h4>{{entity.name}}</h4>
          <p>Type: {{entity.type}}</p>
          <p>Risk Score: {{(entity.riskScore * 100).toFixed(1)}}%</p>
          <div *ngIf="entity.flags?.length" class="flags">
            <span *ngFor="let flag of entity.flags" class="flag">{{flag}}</span>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .entities-container {
      padding: 15px;
    }
    .entity-list {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
      gap: 15px;
      margin-top: 10px;
    }
    .entity-card {
      padding: 15px;
      border-radius: 8px;
      background: white;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .entity-card.high-risk { border-left: 4px solid #ff4444; }
    .entity-card.medium-risk { border-left: 4px solid #ffbb33; }
    .entity-card.low-risk { border-left: 4px solid #00C851; }
    .flags {
      display: flex;
      gap: 5px;
      flex-wrap: wrap;
      margin-top: 10px;
    }
    .flag {
      background-color: #f0f0f0;
      padding: 2px 8px;
      border-radius: 12px;
      font-size: 0.8em;
    }
  `]
})
export class EntitiesListComponent {
  @Input() entities: Entity[] = [];
}