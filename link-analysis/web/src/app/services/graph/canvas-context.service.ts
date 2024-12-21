import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CanvasContextService {
  private context: CanvasRenderingContext2D | null = null;
  private canvas: HTMLCanvasElement | null = null;

  initialize(canvas: HTMLCanvasElement): CanvasRenderingContext2D {
    this.canvas = canvas;
    const ctx = canvas.getContext('2d');
    if (!ctx) {
      throw new Error('Could not get 2D context from canvas');
    }
    this.context = ctx;
    return ctx;
  }

  getContext(): CanvasRenderingContext2D {
    if (!this.context) {
      throw new Error('Canvas context not initialized');
    }
    return this.context;
  }

  clear() {
    if (!this.context || !this.canvas) return;
    this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
  }
}