import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NetworkData } from '../../models/types';
import { NetworkGraphService } from '../../services/network-graph.service';

@Component({
  selector: 'app-network-visualization',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './network-visualization.component.html',
  styleUrls: ['./network-visualization.component.css']
})
export class NetworkVisualizationComponent implements OnChanges {
  @Input() networkData: NetworkData | null = null;
  
  constructor(private networkGraphService: NetworkGraphService) {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['networkData'] && this.networkData) {
      this.networkGraphService.renderGraph(this.networkData);
    }
  }
}