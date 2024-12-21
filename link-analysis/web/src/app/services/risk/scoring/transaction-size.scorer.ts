import { Entity, Transaction } from '../../../models/types';

export function calculateTransactionSizeScore(
  entity: Entity,
  transactions: Transaction[]
): number {
  const entityTransactions = transactions.filter(
    tx => tx.sourceId === entity.id || tx.targetId === entity.id
  );
  const avgAmount = entityTransactions.reduce((sum, tx) => sum + tx.amount, 0) / 
                   Math.max(entityTransactions.length, 1);
  return Math.min(avgAmount / 50000, 1);
}