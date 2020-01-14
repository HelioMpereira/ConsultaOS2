/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TabelaFunciAtribuicao;
import model.TabelaOS;
import model.utils;

/**
 *
 * @author User
 */
public class ConsultasSQL {

    public List<TabelaOS> atualizarTabela(String dtInicial, String dtFinal) throws Throwable {
        utils u = new utils();
        
        
                String sql = "SELECT OS, AnoOS, NumeroOS, NumeroBem, Status, Prefixo, SB, NomeDependencia, ProblemaApresentado, DataHoraAbertura, UF, NumeroContrato,"
                + " Fornecedor, NrSOL, TipoManutencao from controleosgarantia.controleosgarantia "
                + "where DataHoraAbertura between'" + u.dateAoContrario(dtInicial) + "' and '" + u.dateAoContrario(dtFinal) + "' and "
                + "TipoManutencao='corretiva' and status not like'cancelad%' and status not like'PEND.DE FECHAMENTO PELA INTEG.' ";
        List<TabelaOS> consultaOS = new ArrayList<>();

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                TabelaOS to = new TabelaOS();

                to.setOS(resultado.getString("OS"));
                to.setAnoOS(resultado.getInt("AnoOS"));
                to.setNumeroOS(resultado.getInt("NumeroOS"));
                to.setNumeroBem(resultado.getString("NumeroBem"));
                to.setStatus(resultado.getString("Status"));
                to.setPrefixo(resultado.getInt("Prefixo"));
                to.setSB(resultado.getInt("SB"));
                to.setNomeDependencia(resultado.getString("NomeDependencia"));
                to.setProblemaApresentado(resultado.getString("ProblemaApresentado"));
                to.setDataHoraAbertura(resultado.getDate("DataHoraAbertura"));
                to.setUF(resultado.getString("UF"));
                to.setNumeroContrato(resultado.getString("NumeroContrato"));
                to.setFornecedor(resultado.getString("Fornecedor"));
                to.setNrSOL(resultado.getString("NrSOL"));
                to.setTipoManutencao(resultado.getString("TipoManutencao"));

                consultaOS.add(to);
            }

            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consultaOS;
    }
    
    

    public List carregaFunci() throws Throwable {

        String sql = "SELECT funci FROM controleosgarantia.tb_funci ";
        List<String> funci = new ArrayList<>();

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                String funcionario = resultado.getString("funci");
                funci.add(funcionario);

            }

            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funci;
    }

    public List populaTabelaEstado() throws Throwable {

        String sql = "SELECT atribuicao.cd_atribuicao, funci.funci, uf.estado FROM controleosgarantia.tb_atribuicaoestado as atribuicao\n"
                + "left join tb_funci as funci on atribuicao.cd_funci = funci.cd_funci\n"
                + "left join uf as uf on atribuicao.cd_estado = uf.cd_estado; ";
        List<TabelaFunciAtribuicao> funci = new ArrayList<>();

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                TabelaFunciAtribuicao to = new TabelaFunciAtribuicao();
                to.setCd(resultado.getInt("cd_atribuicao"));
                to.setFunci(resultado.getString("funci"));
                to.setEstado(resultado.getString("estado"));

                funci.add(to);

            }

            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funci;

    }

    public boolean updateEstado(List<TabelaFunciAtribuicao> lista) throws Throwable {

        String sql = "INSERT INTO controleosgarantia.tb_atribuicaoestado (cd_funci,cd_estado) values (?, ?) ";
        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);

            for (TabelaFunciAtribuicao info : lista) {

                int funcionario = Integer.parseInt(info.getFunci());
                int estado = Integer.parseInt(info.getEstado());

                stmt.setInt(1, funcionario);
                stmt.setInt(2, estado);

                stmt.execute();

            }

            stmt.close();
            con.fechar();

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public int pegaPkFunci(String funci) throws Throwable {

        String sql = "SELECT cd_funci FROM controleosgarantia.tb_funci where funci = '" + funci + "' ";
        int pk = 0;

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                pk = resultado.getInt("cd_funci");

            }

            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pk;

    }

    public boolean deletaRegistro(List<Integer> cd) throws Throwable {
        
    //    int codigo = 0;

       // String sql = "delete from controleosgarantia.tb_atribuicaoestado where cd_atribuicao = '" + codigo + "' ";
        try {

            Conector con = new Conector();
            PreparedStatement stmt = null;

            for (Integer i : cd) {
                int     codigo = i;
                    String sql = "delete from controleosgarantia.tb_atribuicaoestado where cd_atribuicao = " + codigo + " ";
            
             stmt =   con.conectar().prepareStatement(sql);

                stmt.execute();
            }

            stmt.close();
            con.fechar();

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public List osAbertas(String uf) throws Throwable {

        String sql = "SELECT OS, NumeroBem, Status, Prefixo, SB, NomeDependencia, ProblemaApresentado, DataHoraAbertura, UF, NumeroContrato,\n"
                + " Fornecedor, NrSOL, TipoManutencao from controleosgarantia.controleosgarantia \n"
                + " where TipoManutencao='corretiva' and status not like'cancelad%' and status not like'PEND.DE FECHAMENTO PELA INTEG'  and UF in('" + uf + "')";

        List<TabelaOS> consultaOS = new ArrayList<>();

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                TabelaOS to = new TabelaOS();

                to.setOS(resultado.getString("OS"));

                to.setNumeroBem(resultado.getString("NumeroBem"));
                to.setStatus(resultado.getString("Status"));
                to.setPrefixo(resultado.getInt("Prefixo"));
                to.setSB(resultado.getInt("SB"));
                to.setNomeDependencia(resultado.getString("NomeDependencia"));
                to.setProblemaApresentado(resultado.getString("ProblemaApresentado"));
                to.setDataHoraAbertura(resultado.getDate("DataHoraAbertura"));
                to.setUF(resultado.getString("UF"));
                to.setNumeroContrato(resultado.getString("NumeroContrato"));
                to.setFornecedor(resultado.getString("Fornecedor"));
                to.setNrSOL(resultado.getString("NrSOL"));
                to.setTipoManutencao(resultado.getString("TipoManutencao"));

                consultaOS.add(to);
            }

            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consultaOS;

    }

    public String pegaEstadosFunci(String funci) throws Throwable {
        String uf = "";

        String s = "";

        String sql = "SELECT e.estado from tb_atribuicaoestado as atribuicao\n"
                + "left join tb_funci as f on f.cd_funci = atribuicao.cd_funci\n"
                + "left join uf as e on e.cd_estado = atribuicao.cd_estado where f.funci = '" + funci + "'";

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                s += "'" + resultado.getString("estado") + "'" + ",";

            }

            uf = s;
            if (uf.length() > 0) {
                uf = s.substring(1, s.length() - 2);
            }

            System.out.println(uf);
            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uf;

    }
    
    public int permissao (String funci) throws Throwable {

        String sql = "  SELECT permissao FROM controleosgarantia.tb_funci where funci = '" + funci + "' ";
        int pk = 0;

        try {

            Conector con = new Conector();
            PreparedStatement stmt = con.conectar().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                pk = resultado.getInt("permissao");

            }

            con.fechar();

        } catch (SQLException ex) {

            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pk;

    }

    
    
    

}
