public class ProductDAOProxy implements ProductDAO {
    private final ProductDAO productDAO;
    
    public ProductDAOProxy(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product findById(Long id) {
        System.out.println("Logic of proxy before find ...");
        Product p = this.productDAO.findById(id);
        System.out.println("Logic of proxy after find ...");
        return p;
    }

    @Override
    public Product save(Product product) {
        System.out.println("Logic of proxy before save ...");
        Product p = this.productDAO.save(product);
        System.out.println("Logic of proxy after save ...");
        return p;
    }}
