import { Injectable } from '@angular/core';
import { Entity, Transaction } from '../../models/types';
import { RiskPattern } from '../../models/risk.types';

@Injectable({
  providedIn: 'root'
})
export class PatternDetectionService {
  private patterns: RiskPattern[] = [
    {
      id: 'circular_transfer',
      name: 'Circular Transfer Pattern',
      description: 'Funds moving in a circular pattern between multiple entities, potentially indicating money laundering',
      severity: 'high',
      indicators: [
        'Multiple connected transactions',
        'Similar amounts transferred',
        'Funds returning to origin'
      ]
    },
    {
      id: 'rapid_movement',
      name: 'Rapid Fund Movement',
      description: 'High frequency of transactions in short time periods, possibly indicating structuring or layering',
      severity: 'medium',
      indicators: [
        'High transaction frequency',
        'Varying amounts',
        'Multiple recipients'
      ]
    },
    {
      id: 'structuring',
      name: 'Structuring Pattern',
      description: 'Multiple smaller transactions below reporting thresholds, indicating potential avoidance of detection',
      severity: 'high',
      indicators: [
        'Multiple small transactions',
        'Regular intervals',
        'Just below threshold amounts'
      ]
    },
    {
      id: 'dormant_activity',
      name: 'Dormant Account Activity',
      description: 'Sudden activity in previously dormant accounts',
      severity: 'medium',
      indicators: [
        'Long period of inactivity',
        'Sudden high volume',
        'Multiple transactions'
      ]
    },
    {
      id: 'high_risk_jurisdiction',
      name: 'High-Risk Jurisdiction',
      description: 'Transactions involving entities in high-risk jurisdictions',
      severity: 'high',
      indicators: [
        'Known high-risk locations',
        'Complex transaction paths',
        'Unusual transaction patterns'
      ]
    }
  ];

  detectPatterns(
    entity: Entity,
    transactions: Transaction[],
    timeWindow: number = 7 * 24 * 60 * 60 * 1000
  ): RiskPattern[] {
    const detectedPatterns: RiskPattern[] = [];
    const entityTransactions = this.getRecentTransactions(entity, transactions, timeWindow);

    // Detect circular transfers
    if (this.detectCircularTransfer(entity, entityTransactions)) {
      detectedPatterns.push(this.patterns[0]);
    }

    // Detect rapid movements
    if (this.detectRapidMovement(entityTransactions)) {
      detectedPatterns.push(this.patterns[1]);
    }

    // Detect structuring
    if (this.detectStructuring(entityTransactions)) {
      detectedPatterns.push(this.patterns[2]);
    }

    // Detect dormant account activity
    if (this.detectDormantActivity(entityTransactions)) {
      detectedPatterns.push(this.patterns[3]);
    }

    // Detect high-risk jurisdiction activity
    if (this.detectHighRiskJurisdiction(entity)) {
      detectedPatterns.push(this.patterns[4]);
    }

    return detectedPatterns;
  }

  private getRecentTransactions(
    entity: Entity,
    transactions: Transaction[],
    timeWindow: number
  ): Transaction[] {
    const now = new Date().getTime();
    return transactions.filter(tx => 
      (tx.sourceId === entity.id || tx.targetId === entity.id) &&
      (now - new Date(tx.timestamp).getTime()) <= timeWindow
    );
  }

  private detectCircularTransfer(entity: Entity, transactions: Transaction[]): boolean {
    return transactions.some(tx => 
      transactions.some(tx2 => 
        tx.targetId === tx2.sourceId && 
        tx2.targetId === entity.id &&
        Math.abs(tx.amount - tx2.amount) < 1000 &&
        Math.abs(new Date(tx2.timestamp).getTime() - new Date(tx.timestamp).getTime()) < 24 * 60 * 60 * 1000
      )
    );
  }

  private detectRapidMovement(transactions: Transaction[]): boolean {
    if (transactions.length < 3) return false;
    
    const sorted = [...transactions].sort((a, b) => 
      new Date(a.timestamp).getTime() - new Date(b.timestamp).getTime()
    );
    
    for (let i = 2; i < sorted.length; i++) {
      const timeDiff = new Date(sorted[i].timestamp).getTime() - 
                      new Date(sorted[i-2].timestamp).getTime();
      if (timeDiff <= 24 * 60 * 60 * 1000) {
        return true;
      }
    }
    return false;
  }

  private detectStructuring(transactions: Transaction[]): boolean {
    const threshold = 10000;
    const smallTransactions = transactions.filter(tx => tx.amount < threshold);
    
    if (smallTransactions.length < 3) return false;
    
    const sorted = [...smallTransactions].sort((a, b) => 
      new Date(a.timestamp).getTime() - new Date(b.timestamp).getTime()
    );
    
    let regularIntervals = 0;
    for (let i = 1; i < sorted.length; i++) {
      const interval = new Date(sorted[i].timestamp).getTime() - 
                      new Date(sorted[i-1].timestamp).getTime();
      if (interval < 24 * 60 * 60 * 1000) {
        regularIntervals++;
      }
    }
    
    return regularIntervals >= 2;
  }

  private detectDormantActivity(transactions: Transaction[]): boolean {
    if (transactions.length < 2) return false;

    const sorted = [...transactions].sort((a, b) => 
      new Date(a.timestamp).getTime() - new Date(b.timestamp).getTime()
    );

    const dormancyPeriod = 90 * 24 * 60 * 60 * 1000; // 90 days
    const activityWindow = 7 * 24 * 60 * 60 * 1000; // 7 days

    for (let i = 1; i < sorted.length; i++) {
      const gap = new Date(sorted[i].timestamp).getTime() - 
                 new Date(sorted[i-1].timestamp).getTime();
      
      if (gap > dormancyPeriod) {
        const subsequentActivity = sorted.slice(i).filter(tx => 
          new Date(tx.timestamp).getTime() - new Date(sorted[i].timestamp).getTime() <= activityWindow
        );
        
        if (subsequentActivity.length >= 3) {
          return true;
        }
      }
    }
    return false;
  }

  private detectHighRiskJurisdiction(entity: Entity): boolean {
    return entity.flags?.some(flag => 
      flag.includes('high_risk_jurisdiction') || 
      flag.includes('sanctioned_country') ||
      flag.includes('offshore')
    ) || false;
  }
}