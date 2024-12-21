import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GraphZoomService {
  private zoomLevel = new BehaviorSubject<number>(1);
  zoomLevel$ = this.zoomLevel.asObservable();

  private readonly MIN_ZOOM = 0.2;
  private readonly MAX_ZOOM = 3;

  zoomIn() {
    const newZoom = Math.min(this.zoomLevel.value * 1.2, this.MAX_ZOOM);
    this.zoomLevel.next(newZoom);
  }

  zoomOut() {
    const newZoom = Math.max(this.zoomLevel.value * 0.8, this.MIN_ZOOM);
    this.zoomLevel.next(newZoom);
  }

  resetZoom() {
    this.zoomLevel.next(1);
  }
}