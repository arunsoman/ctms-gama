import { Entity, Transaction } from '../../../models/types';

export function calculateNetworkCentralityScore(
  entity: Entity,
  networkData: { entities: Entity[], transactions: Transaction[] }
): number {
  const connections = networkData.transactions.filter(
    tx => tx.sourceId === entity.id || tx.targetId === entity.id
  ).length;
  return Math.min(connections / networkData.entities.length, 1);
}