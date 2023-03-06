public interface ProductDAO{
    public Product findById(Long id);
    public Product save(Product product);
}