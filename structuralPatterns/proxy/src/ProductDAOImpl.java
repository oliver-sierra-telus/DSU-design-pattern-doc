public class ProductDAOImpl implements ProductDAO {

    @Override
    public Product findById(Long id) {
        System.out.println("Finding product with id " + id);
        return new Product(id, "Sample");
    }

    @Override
    public Product save(Product product) {
        System.out.println("Saving product...");
        return product;
    }
    
}
