import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NetworkGraphComponent } from '../network-graph/network-graph.component';
import { NetworkFiltersComponent } from '../network-filters/network-filters.component';
import { RiskSummaryComponent } from '../risk-summary/risk-summary.component';
import { AlertsComponent } from '../alerts/alerts.component';
import { RiskAnalysisComponent } from '../risk-analysis/risk-analysis.component';
import { FilterCriteria } from '../../models/types';
import { MockDataService } from '../../services/mock-data.service';
import { NetworkDataService } from '../../services/network-data.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    NetworkGraphComponent,
    NetworkFiltersComponent,
    RiskSummaryComponent,
    AlertsComponent,
    RiskAnalysisComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  constructor(
    private mockDataService: MockDataService,
    private networkDataService: NetworkDataService
  ) {}

  onFilterChange(filters: FilterCriteria) {
    this.mockDataService.getNetworkData().subscribe(data => {
      const filteredEntities = data.entities.filter(entity => {
        const matchesType = filters.entityTypes.length === 0 || 
          filters.entityTypes.includes(entity.type);
        const matchesRisk = entity.riskScore >= filters.riskThreshold;
        return matchesType && matchesRisk;
      });

      const filteredTransactions = data.transactions.filter(tx => {
        if (!filters.dateRange.start || !filters.dateRange.end) return true;
        const txDate = new Date(tx.timestamp);
        const start = new Date(filters.dateRange.start);
        const end = new Date(filters.dateRange.end);
        return txDate >= start && txDate <= end;
      });

      this.networkDataService.updateNetworkData({
        entities: filteredEntities,
        transactions: filteredTransactions
      });
    });
  }
}