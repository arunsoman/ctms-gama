import { Injectable } from '@angular/core';
import { NetworkData } from '../models/types';

@Injectable({
  providedIn: 'root'
})
export class NetworkGraphService {
  private canvas: HTMLCanvasElement | null = null;
  private ctx: CanvasRenderingContext2D | null = null;
  private scale = 1;

  renderGraph(data: NetworkData) {
    if (!this.canvas || !this.ctx) return;
    
    // Clear canvas
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    
    // Draw nodes and edges
    this.drawEntities(data.entities);
    this.drawTransactions(data.transactions);
  }

  private drawEntities(entities: Entity[]) {
    // Implementation for drawing entity nodes
  }

  private drawTransactions(transactions: Transaction[]) {
    // Implementation for drawing transaction edges
  }

  zoomIn() {
    this.scale *= 1.2;
    this.updateTransform();
  }

  zoomOut() {
    this.scale *= 0.8;
    this.updateTransform();
  }

  resetView() {
    this.scale = 1;
    this.updateTransform();
  }

  private updateTransform() {
    if (!this.ctx) return;
    this.ctx.setTransform(this.scale, 0, 0, this.scale, 0, 0);
  }
}