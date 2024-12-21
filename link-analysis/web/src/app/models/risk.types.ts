export interface RiskFactor {
  id: string;
  name: string;
  weight: number;
  description: string;
}

export interface RiskPattern {
  id: string;
  name: string;
  description: string;
  severity: 'high' | 'medium' | 'low';
  indicators: string[];
}

export interface RiskScore {
  entityId: string;
  score: number;
  factors: { [key: string]: number };
  patterns: RiskPattern[];
  timestamp: Date;
}

export interface RiskReport {
  id: string;
  entityId: string;
  timestamp: Date;
  overallScore: number;
  score: RiskScore;
  detectedPatterns: RiskPattern[];
  recommendations: string[];
}