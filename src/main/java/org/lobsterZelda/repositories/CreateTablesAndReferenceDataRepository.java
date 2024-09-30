package org.lobsterZelda.repositories;

public interface CreateTablesAndReferenceDataRepository
{
    void createAndInitializeAllTables();
    void deleteAllTables();
}
