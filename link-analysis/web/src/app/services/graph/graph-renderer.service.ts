import { Injectable } from '@angular/core';
import { Entity, Transaction } from '../../models/types';
import { GraphConfigService } from './graph-config.service';
import { GraphLayoutService } from './graph-layout.service';
import { CanvasContextService } from './canvas-context.service';
import { GraphZoomService } from './graph-zoom.service';

@Injectable({
  providedIn: 'root'
})
export class GraphRendererService {
  private scale = 0.8;
  private offsetX = 0;
  private offsetY = 0;
  private currentRenderData: { entities: Entity[], transactions: Transaction[] } | null = null;

  constructor(
    private config: GraphConfigService,
    private layout: GraphLayoutService,
    private canvasService: CanvasContextService,
    private zoomService: GraphZoomService
  ) {
    this.zoomService.zoomLevel$.subscribe(zoom => {
      this.scale = zoom * 0.8;
      if (this.currentRenderData) {
        this.render(this.currentRenderData.entities, this.currentRenderData.transactions);
      }
    });
  }

  initialize(canvas: HTMLCanvasElement) {
    const ctx = this.canvasService.initialize(canvas);
    const dpr = window.devicePixelRatio || 1;
    canvas.width = Math.floor(canvas.width * dpr);
    canvas.height = Math.floor(canvas.height * dpr);
    canvas.style.width = `${canvas.width / dpr}px`;
    canvas.style.height = `${canvas.height / dpr}px`;
    ctx.scale(dpr, dpr);
  }

  render(entities: Entity[], transactions: Transaction[]) {
    this.currentRenderData = { entities, transactions };
    const ctx = this.canvasService.getContext();
    this.canvasService.clear();
    
    ctx.save();
    
    const centerX = ctx.canvas.width / 2;
    const centerY = ctx.canvas.height / 2;
    ctx.translate(centerX + this.offsetX, centerY + this.offsetY);
    ctx.scale(this.scale, this.scale);
    ctx.translate(-centerX, -centerY);
    
    const width = ctx.canvas.width;
    const height = ctx.canvas.height;
    this.layout.calculateLayout(entities, width, height);
    
    this.drawTransactions(transactions);
    this.drawEntities(entities);
    
    ctx.restore();
  }

  private drawEntities(entities: Entity[]) {
    const ctx = this.canvasService.getContext();
    
    entities.forEach(entity => {
      const pos = this.layout.getNodePosition(entity.id);
      const color = this.config.getNodeColor(entity.type);
      const strokeColor = this.config.getRiskColor(entity.riskScore);
      
      // Draw node circle
      ctx.beginPath();
      ctx.arc(pos.x, pos.y, this.config.nodeRadius, 0, Math.PI * 2);
      ctx.fillStyle = color;
      ctx.fill();
      ctx.strokeStyle = strokeColor;
      ctx.lineWidth = 3;
      ctx.stroke();
      
      // Draw label
      ctx.fillStyle = '#333';
      ctx.font = '12px Arial';
      ctx.textAlign = 'center';
      ctx.fillText(entity.name, pos.x, pos.y + this.config.nodeRadius + 15);
    });
  }

  private drawTransactions(transactions: Transaction[]) {
    const ctx = this.canvasService.getContext();
    
    transactions.forEach(tx => {
      const source = this.layout.getNodePosition(tx.sourceId);
      const target = this.layout.getNodePosition(tx.targetId);
      
      // Draw line
      ctx.beginPath();
      ctx.moveTo(source.x, source.y);
      ctx.lineTo(target.x, target.y);
      ctx.strokeStyle = this.config.getRiskColor(tx.riskScore);
      ctx.lineWidth = 2;
      ctx.stroke();
      
      // Draw arrow
      const angle = Math.atan2(target.y - source.y, target.x - source.x);
      const arrowLength = 10;
      
      const arrowX = target.x - this.config.nodeRadius * Math.cos(angle);
      const arrowY = target.y - this.config.nodeRadius * Math.sin(angle);
      
      ctx.beginPath();
      ctx.moveTo(
        arrowX - arrowLength * Math.cos(angle - Math.PI / 6),
        arrowY - arrowLength * Math.sin(angle - Math.PI / 6)
      );
      ctx.lineTo(arrowX, arrowY);
      ctx.lineTo(
        arrowX - arrowLength * Math.cos(angle + Math.PI / 6),
        arrowY - arrowLength * Math.sin(angle + Math.PI / 6)
      );
      ctx.fillStyle = this.config.getRiskColor(tx.riskScore);
      ctx.fill();
    });
  }
}