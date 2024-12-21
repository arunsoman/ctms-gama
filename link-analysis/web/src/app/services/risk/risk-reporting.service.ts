import { Injectable } from '@angular/core';
import { Entity, Transaction } from '../../models/types';
import { RiskScore, RiskReport, RiskPattern } from '../../models/risk.types';
import { RiskScoringService } from './risk-scoring.service';
import { PatternDetectionService } from './pattern-detection.service';

@Injectable({
  providedIn: 'root'
})
export class RiskReportingService {
  constructor(
    private riskScoringService: RiskScoringService,
    private patternDetectionService: PatternDetectionService
  ) {}

  generateEntityReport(
    entity: Entity,
    transactions: Transaction[],
    networkData: { entities: Entity[], transactions: Transaction[] }
  ): RiskReport {
    const score = this.riskScoringService.calculateEntityRiskScore(
      entity,
      transactions,
      networkData
    );

    const patterns = this.patternDetectionService.detectPatterns(
      entity,
      transactions
    );

    const recommendations = this.generateRecommendations(score, patterns);

    return {
      id: `report-${entity.id}-${Date.now()}`,
      entityId: entity.id,
      timestamp: new Date(),
      overallScore: score.score,
      score,
      detectedPatterns: patterns,
      recommendations
    };
  }

  private generateRecommendations(
    riskScore: RiskScore,
    patterns: RiskPattern[]
  ): string[] {
    const recommendations: string[] = [];

    if (riskScore.score >= 0.7) {
      recommendations.push('Immediate review required due to high risk score');
    }

    if (riskScore.factors['transaction_volume'] > 0.8) {
      recommendations.push('Review unusual transaction volume');
    }

    patterns.forEach(pattern => {
      switch (pattern.id) {
        case 'circular_transfer':
          recommendations.push('Investigate circular transaction patterns');
          break;
        case 'rapid_movement':
          recommendations.push('Monitor high-frequency transaction behavior');
          break;
        case 'structuring':
          recommendations.push('Review potential structuring activity');
          break;
      }
    });

    return recommendations;
  }
}