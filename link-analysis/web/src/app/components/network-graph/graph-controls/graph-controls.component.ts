import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GraphZoomService } from '../../../services/graph/graph-zoom.service';

@Component({
  selector: 'app-graph-controls',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="controls">
      <button (click)="zoomService.zoomIn()">+</button>
      <button (click)="zoomService.zoomOut()">-</button>
      <button (click)="zoomService.resetZoom()">Reset</button>
    </div>
  `,
  styles: [`
    .controls {
      position: absolute;
      top: 10px;
      right: 10px;
      display: flex;
      gap: 5px;
      z-index: 1;
    }
    
    button {
      padding: 8px;
      border: none;
      background: white;
      border-radius: 4px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      cursor: pointer;
    }
    
    button:hover {
      background: #f5f5f5;
    }
  `]
})
export class GraphControlsComponent {
  constructor(public zoomService: GraphZoomService) {}
}