import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GraphCanvasComponent } from './graph-canvas/graph-canvas.component';
import { GraphControlsComponent } from './graph-controls/graph-controls.component';
import { EntitiesListComponent } from './entities-list/entities-list.component';
import { NetworkDataService } from '../../services/network-data.service';
import { GraphRendererService } from '../../services/graph/graph-renderer.service';
import { GraphZoomService } from '../../services/graph/graph-zoom.service';
import { CanvasContextService } from '../../services/graph/canvas-context.service';

@Component({
  selector: 'app-network-graph',
  standalone: true,
  imports: [
    CommonModule,
    GraphCanvasComponent,
    GraphControlsComponent,
    EntitiesListComponent
  ],
  providers: [
    GraphRendererService,
    GraphZoomService,
    CanvasContextService
  ],
  template: `
    <div class="network-graph">
      <div class="graph-section">
        <app-graph-controls></app-graph-controls>
        <app-graph-canvas></app-graph-canvas>
      </div>
      <div class="entities-section">
        <app-entities-list [entities]="networkData?.entities || []"></app-entities-list>
      </div>
    </div>
  `,
  styles: [`
    .network-graph {
      width: 100%;
      height: 100%;
      display: grid;
      grid-template-columns: 1fr 300px;
      gap: 20px;
    }
    .graph-section {
      position: relative;
      background: white;
      border-radius: 8px;
      overflow: hidden;
    }
    .entities-section {
      background: white;
      border-radius: 8px;
      overflow-y: auto;
    }
  `]
})
export class NetworkGraphComponent {
  networkData = this.networkDataService.getCurrentData();

  constructor(private networkDataService: NetworkDataService) {}
}