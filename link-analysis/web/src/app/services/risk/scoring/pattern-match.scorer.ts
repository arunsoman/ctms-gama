import { Entity, Transaction } from '../../../models/types';

export function calculatePatternMatchScore(
  entity: Entity,
  transactions: Transaction[]
): number {
  return entity.flags ? Math.min(entity.flags.length * 0.2, 1) : 0;
}