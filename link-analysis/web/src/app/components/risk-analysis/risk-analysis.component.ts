import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NetworkDataService } from '../../services/network-data.service';
import { RiskReportingService } from '../../services/risk/risk-reporting.service';
import { RiskScoringService } from '../../services/risk/risk-scoring.service';
import { RiskReport } from '../../models/risk.types';
import { Entity, Transaction, NetworkData } from '../../models/types';
import { RiskComparisonComponent } from './comparison/risk-comparison.component';
import { RiskPatternsComponent } from './patterns/risk-patterns.component';
import { RiskFactorBreakdownComponent } from './breakdown/risk-factor-breakdown.component';
import { TransactionTimelineComponent } from './timeline/transaction-timeline.component';

@Component({
  selector: 'app-risk-analysis',
  standalone: true,
  imports: [
    CommonModule,
    RiskComparisonComponent,
    RiskPatternsComponent,
    RiskFactorBreakdownComponent,
    TransactionTimelineComponent
  ],
  template: `
    <div class="risk-analysis-container">
      <div class="header">
        <h2>Risk Analysis</h2>
        <div class="entity-selector">
          <select (change)="onEntitySelect($event)" class="entity-select">
            <option *ngFor="let entity of entities" [value]="entity.id">
              {{entity.name}} (Risk: {{(entity.riskScore * 100).toFixed(0)}}%)
            </option>
          </select>
        </div>
      </div>

      <div class="risk-dashboard" *ngIf="currentReport">
        <div class="main-section">
          <div class="score-section">
            <div class="overall-score" [class]="getRiskLevel(currentReport.overallScore)">
              <h3>Overall Risk Score</h3>
              <div class="score">{{(currentReport.overallScore * 100).toFixed(0)}}%</div>
            </div>
            <app-risk-factor-breakdown
              [riskScore]="currentReport.score"
              [riskFactors]="riskFactors">
            </app-risk-factor-breakdown>
          </div>

          <app-risk-patterns
            [patterns]="currentReport.detectedPatterns">
          </app-risk-patterns>
        </div>

        <div class="side-section">
          <app-risk-comparison
            [entities]="entities">
          </app-risk-comparison>

          <app-transaction-timeline
            [transactions]="entityTransactions">
          </app-transaction-timeline>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./risk-analysis.component.css']
})
export class RiskAnalysisComponent implements OnInit {
  currentReport: RiskReport | null = null;
  reports: RiskReport[] = [];
  entities: Entity[] = [];
  entityTransactions: Transaction[] = [];
  riskFactors = this.riskScoringService.riskFactors;

  constructor(
    private networkDataService: NetworkDataService,
    private riskReportingService: RiskReportingService,
    private riskScoringService: RiskScoringService
  ) {}

  ngOnInit() {
    this.networkDataService.networkData$.subscribe(data => {
      if (!data) return;
      
      this.entities = data.entities;
      this.reports = data.entities.map(entity => 
        this.riskReportingService.generateEntityReport(
          entity,
          data.transactions,
          data
        )
      );
      
      if (this.reports.length > 0) {
        this.currentReport = this.reports[0];
        this.updateEntityTransactions(data);
      }
    });
  }

  onEntitySelect(event: Event) {
    const select = event.target as HTMLSelectElement;
    const entityId = select.value;
    this.currentReport = this.reports.find(r => r.entityId === entityId) || null;
    if (this.currentReport) {
      this.updateEntityTransactions(this.networkDataService.getCurrentData());
    }
  }

  private updateEntityTransactions(data: NetworkData | null) {
    if (!data || !this.currentReport) return;
    this.entityTransactions = data.transactions.filter(tx =>
      tx.sourceId === this.currentReport?.entityId || 
      tx.targetId === this.currentReport?.entityId
    );
  }

  getRiskLevel(score: number): string {
    if (score >= 0.7) return 'high';
    if (score >= 0.4) return 'medium';
    return 'low';
  }
}