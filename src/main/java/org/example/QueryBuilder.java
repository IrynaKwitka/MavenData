package org.example;

public class QueryBuilder {

        protected String query_;

        public QueryBuilder() {
            query_ = "";
        }

        public QueryBuilder select(String... columns) {
            query_ += "SELECT ";
            for (String column : columns) {
                query_ += column + ", ";
            }
            query_ = query_.substring(0, query_.length() - 2);
            query_ += " ";
            return this;
        }

        public QueryBuilder from(String table) {
            query_ += "FROM " + table + " ";
            return this;
        }

        public QueryBuilder where(String condition) {
            query_ += "WHERE " + condition + " ";
            return this;
        }

        public QueryBuilder and(String condition) {
            query_ += "AND " + condition + " ";
            return this;
        }

        public QueryBuilder or(String condition) {
            query_ += "OR " + condition + " ";
            return this;
        }

        public QueryBuilder orderBy(String column) {
            query_ += "ORDER BY " + column + " ";
            return this;
        }

        public QueryBuilder asc() {
            query_ += "ASC ";
            return this;
        }

        public QueryBuilder desc() {
            query_ += "DESC ";
            return this;
        }

        public QueryBuilder limit(int limit) {
            query_ += "LIMIT " + limit + " ";
            return this;
        }

        public QueryBuilder offset(int offset) {
            query_ += "OFFSET " + offset + " ";
            return this;
        }

        public String build() {
            return query_;
        }

        public void reset() {
            query_ = "";
        }

        public static void main(String[] args) {
            QueryBuilder queryBuilder = new QueryBuilder();
            String query = queryBuilder.select("id", "name").from("users").where("id = 1").build();
            System.out.println(query);
        }
}
