import br.com.raijheckinny.dao.ClienteDAO;
import br.com.raijheckinny.dao.IClienteDAO;
import br.com.raijheckinny.dao.jdbc.ConnectionFactory;
import br.com.raijheckinny.domin.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteTest {
    IClienteDAO clienteDAO = null;
    Cliente cliente = null;

    @Before
    public void setUp(){
        clienteDAO = new ClienteDAO();
        cliente = new Cliente();
    }

    @Test
    public void cadastrarTest() throws SQLException {
        cliente.setNome("Rai Jheckinny");
        cliente.setCodigo("10");

        Integer resCadastro= clienteDAO.cadastrarCliente(cliente);
        Assert.assertNotNull("O resultado do Cadastro nao deve ser nulo",resCadastro);
        Assert.assertEquals("O resultado do cadastro deve ser 1", Integer.valueOf(1),resCadastro);

        Cliente clienteBD = clienteDAO.buscarCliente("10");
        Assert.assertNotNull("O resultado de buscar nao deve ser null", clienteBD);
        Assert.assertEquals("O Nome resultado Pelo Busca devem der iquais", cliente.getNome(), clienteBD.getNome());
        Assert.assertEquals("O Codigo resultado Pelo Busca devem der iquais", cliente.getCodigo(), clienteBD.getCodigo());

    }
}
