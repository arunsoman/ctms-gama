import { Injectable } from '@angular/core';
import { Entity } from '../../models/types';

interface NodePosition {
  x: number;
  y: number;
}

@Injectable({
  providedIn: 'root'
})
export class GraphLayoutService {
  private positions = new Map<string, NodePosition>();
  
  calculateLayout(entities: Entity[], width: number, height: number) {
    const centerX = width / 2;
    const centerY = height / 2;
    // Reduce the radius to make the graph smaller by default
    const radius = Math.min(width, height) * 0.25; // Changed from 0.35 to 0.25
    
    entities.forEach((entity, index) => {
      const angle = (index / entities.length) * 2 * Math.PI;
      this.positions.set(entity.id, {
        x: centerX + radius * Math.cos(angle),
        y: centerY + radius * Math.sin(angle)
      });
    });
    
    return this.positions;
  }

  getNodePosition(id: string): NodePosition {
    return this.positions.get(id) || { x: 0, y: 0 };
  }
}