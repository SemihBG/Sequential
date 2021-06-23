package com.semihbkgr.sequential.server.transaction;

/*
@Component
@Transactional
@Slf4j
public class ListTransaction {

    @PersistenceContext
    private EntityManager entityManager;

    public void createTable(String tableName) {

        entityManager.createNativeQuery(
                "CREATE TABLE "+tableName+"" +
                        " (id INT NOT NULL AUTO_INCREMENT," +
                        "eng varchar(25) NOT NULL," +
                        "tr varchar(25) NOT NULL," +
                        "PRIMARY KEY (id))").
                executeUpdate();

        log.info("table created, table name = "+tableName);

    }

    public void deleteTable(String tableName) {

        entityManager.createNativeQuery("DROP TABLE "+tableName).executeUpdate();

        log.info("table deleted, table name = "+tableName);
    }


}
*/