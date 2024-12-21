import { Component, ElementRef, ViewChild, AfterViewInit, OnDestroy, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NetworkDataService } from '../../../services/network-data.service';
import { GraphRendererService } from '../../../services/graph/graph-renderer.service';
import { NetworkData } from '../../../models/types';
import { ResizeService } from '../../../services/resize.service';

@Component({
  selector: 'app-graph-canvas',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="canvas-container">
      <canvas #canvas></canvas>
    </div>
  `,
  styles: [`
    .canvas-container {
      width: 100%;
      height: 100%;
      position: relative;
    }
    canvas {
      width: 100%;
      height: 100%;
      display: block;
    }
  `],
  providers: [ResizeService]
})
export class GraphCanvasComponent implements AfterViewInit, OnDestroy {
  @ViewChild('canvas') canvasRef!: ElementRef<HTMLCanvasElement>;
  private subscription = new Subscription();
  private networkDataService = inject(NetworkDataService);
  private renderer = inject(GraphRendererService);
  private resizeService = inject(ResizeService);

  ngAfterViewInit() {
    this.initCanvas();
    this.setupSubscriptions();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  private initCanvas() {
    const canvas = this.canvasRef.nativeElement;
    const container = canvas.parentElement;
    if (!container) return;

    const { width, height } = container.getBoundingClientRect();
    canvas.width = width;
    canvas.height = height;
    this.renderer.initialize(canvas);
    
    const currentData = this.networkDataService.getCurrentData();
    if (currentData) {
      this.updateCanvas(currentData);
    }
  }

  private setupSubscriptions() {
    this.subscription.add(
      this.networkDataService.networkData$.subscribe(
        data => data && this.updateCanvas(data)
      )
    );

    const container = this.canvasRef.nativeElement.parentElement;
    if (container) {
      this.subscription.add(
        this.resizeService.observeResize(container)
          .pipe(debounceTime(100))
          .subscribe(() => {
            const currentData = this.networkDataService.getCurrentData();
            if (currentData) {
              this.updateCanvas(currentData);
            }
          })
      );
    }
  }

  private updateCanvas(data: NetworkData) {
    const canvas = this.canvasRef.nativeElement;
    const container = canvas.parentElement;
    if (!container) return;

    const { width, height } = container.getBoundingClientRect();
    if (canvas.width !== width || canvas.height !== height) {
      canvas.width = width;
      canvas.height = height;
      this.renderer.initialize(canvas);
    }
    this.renderer.render(data.entities, data.transactions);
  }
}