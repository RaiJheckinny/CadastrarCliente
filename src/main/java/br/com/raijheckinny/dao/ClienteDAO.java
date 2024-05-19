package br.com.raijheckinny.dao;

import br.com.raijheckinny.dao.jdbc.ConnectionFactory;
import br.com.raijheckinny.domin.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public Integer cadastrarCliente(Cliente cliente) throws SQLException {

        Connection connection = null;
        PreparedStatement stm = null;

        try{
            connection = ConnectionFactory.getConnetion();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosCadastrar(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e){
            throw e;
        } finally {
            closeConnection(connection,stm,null);
        }
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(connection != null){
                connection.close();
            }

        } catch (SQLException e1){
            e1.printStackTrace();
        }
    }

    private void adicionarParametrosCadastrar(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCodigo());

    }

    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_cliente VALUES ");
        sb.append("(NEXTVAL('sq_cliente'), ? , ?);");

        return sb.toString();
    }

    @Override
    public Integer atualizarCliente(Cliente cliente) {
        return 0;
    }

    @Override
    public Integer removerCliente(Cliente cliente) {
        return 0;
    }

    @Override
    public Cliente buscarCliente(String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try{
            connection = ConnectionFactory.getConnetion();
            String sql =  getSqlBuscar();
            stm = connection.prepareStatement(sql);
            adicionarParametrosBuscar(stm, codigo);
            rs = stm.executeQuery();

            if(rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");

                cliente.setNome(nome);
                cliente.setId(id);
                cliente.setCodigo(codigo);
            }
            return cliente;
        } catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection,stm,rs);
        }
    }

    private void adicionarParametrosBuscar(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    private String getSqlBuscar() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_cliente ");
        sb.append("WHERE CODIGO = ?");

        return sb.toString();
    }

    @Override
    public List<Cliente> buscarTodos() {
        return List.of();
    }
}
