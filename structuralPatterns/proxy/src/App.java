public class App {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl();
        ProductDAOProxy productDAOProxy = new ProductDAOProxy(productDAO);
        Product myProduct = productDAOProxy.findById(10L);
        productDAOProxy.save(myProduct);     
    }
}
