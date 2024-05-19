package br.com.raijheckinny.dao;

import br.com.raijheckinny.domin.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO {
    public Integer cadastrarCliente(Cliente cliente) throws SQLException;
    public Integer atualizarCliente(Cliente cliente) throws SQLException;
    public Integer removerCliente(Cliente cliente) throws SQLException;
    public Cliente buscarCliente(String codigo) throws SQLException;
    public List<Cliente> buscarTodos() throws SQLException;
}
