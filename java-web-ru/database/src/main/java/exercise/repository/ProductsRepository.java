package exercise.repository;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?);";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                product.setId(generatedKey.getLong(1));
            } else {
                throw new SQLException("DB have not returned an ID after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?;";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product foundProduct = new Product(title, price);
                foundProduct.setId(id);
                return Optional.of(foundProduct);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException{
        String sql = "SELECT * FROM products;";
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }
    // END
}
