import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FilterCriteria } from '../../models/types';

@Component({
  selector: 'app-network-filters',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './network-filters.component.html',
  styleUrls: ['./network-filters.component.css']
})
export class NetworkFiltersComponent {
  @Output() filterChange = new EventEmitter<FilterCriteria>();
  
  filters: FilterCriteria = {
    dateRange: { start: null, end: null },
    riskThreshold: 0,
    entityTypes: []
  };

  toggleEntityType(event: Event) {
    const checkbox = event.target as HTMLInputElement;
    if (checkbox.checked) {
      this.filters.entityTypes.push(checkbox.value);
    } else {
      this.filters.entityTypes = this.filters.entityTypes.filter(
        type => type !== checkbox.value
      );
    }
    this.applyFilters();
  }

  applyFilters() {
    this.filterChange.emit(this.filters);
  }
}