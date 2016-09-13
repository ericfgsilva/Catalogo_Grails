class ApplicationDataSource {

   boolean pooling = true

   String dbCreate = "create-drop" // 'create', 'create-drop','update'

   String url = "jdbc:hsqldb:mem:catalogo"

   String driverClassName = "org.hsqldb.jdbcDriver"

   String username = "sa"

   String password = ""
}