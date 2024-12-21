import { Entity, Transaction } from '../../../models/types';

export function calculateTransactionVolumeScore(
  entity: Entity,
  transactions: Transaction[]
): number {
  const entityTransactions = transactions.filter(
    tx => tx.sourceId === entity.id || tx.targetId === entity.id
  );
  return Math.min(entityTransactions.length / 10, 1);
}