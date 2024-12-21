import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RiskAnalysisComponent } from './risk-analysis.component';
import { RiskComparisonComponent } from './comparison/risk-comparison.component';
import { RiskPatternsComponent } from './patterns/risk-patterns.component';
import { RiskFactorBreakdownComponent } from './breakdown/risk-factor-breakdown.component';
import { TransactionTimelineComponent } from './timeline/transaction-timeline.component';
import { RiskTrendChartComponent } from './charts/risk-trend-chart.component';

@NgModule({
  imports: [
    CommonModule,
    RiskAnalysisComponent,
    RiskComparisonComponent,
    RiskPatternsComponent,
    RiskFactorBreakdownComponent,
    TransactionTimelineComponent,
    RiskTrendChartComponent
  ],
  exports: [RiskAnalysisComponent]
})
export class RiskAnalysisModule {}