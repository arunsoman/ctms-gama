import { Injectable } from '@angular/core';
import { Entity, Transaction } from '../../models/types';
import { RiskFactor, RiskScore } from '../../models/risk.types';
import { calculateTransactionVolumeScore } from './scoring/transaction-volume.scorer';
import { calculateTransactionSizeScore } from './scoring/transaction-size.scorer';
import { calculateNetworkCentralityScore } from './scoring/network-centrality.scorer';
import { calculatePatternMatchScore } from './scoring/pattern-match.scorer';

@Injectable({
  providedIn: 'root'
})
export class RiskScoringService {
  readonly riskFactors: RiskFactor[] = [
    {
      id: 'transaction_volume',
      name: 'Transaction Volume',
      weight: 0.3,
      description: 'High volume of transactions in short time periods'
    },
    {
      id: 'transaction_size',
      name: 'Transaction Size',
      weight: 0.25,
      description: 'Unusually large transaction amounts'
    },
    {
      id: 'network_centrality',
      name: 'Network Centrality',
      weight: 0.25,
      description: 'Entity\'s position and connectivity in the network'
    },
    {
      id: 'pattern_matches',
      name: 'Suspicious Patterns',
      weight: 0.2,
      description: 'Matches with known suspicious patterns'
    }
  ];

  calculateEntityRiskScore(
    entity: Entity,
    transactions: Transaction[],
    networkData: { entities: Entity[], transactions: Transaction[] }
  ): RiskScore {
    const factors: { [key: string]: number } = {};
    
    factors['transaction_volume'] = calculateTransactionVolumeScore(entity, transactions);
    factors['transaction_size'] = calculateTransactionSizeScore(entity, transactions);
    factors['network_centrality'] = calculateNetworkCentralityScore(entity, networkData);
    factors['pattern_matches'] = calculatePatternMatchScore(entity, transactions);

    const overallScore = this.riskFactors.reduce((score, factor) => {
      return score + (factors[factor.id] * factor.weight);
    }, 0);

    return {
      entityId: entity.id,
      score: overallScore,
      factors,
      patterns: [],
      timestamp: new Date()
    };
  }
}