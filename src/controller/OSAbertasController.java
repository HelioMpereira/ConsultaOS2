/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.ConsultasSQL;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TabelaOS;
import model.utils;
import poi.GeraExcel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OSAbertasController extends  AbstractController implements Initializable {
    
    
    @FXML
    private TableColumn<TabelaOS, String> col_NumeroBem;
    @FXML
    private TableColumn<TabelaOS, String> col_Status;
    @FXML
    private TableColumn<TabelaOS, Integer> col_Prefixo;
    @FXML
    private TableColumn<TabelaOS, Integer> col_SB;
    @FXML
    private TableColumn<TabelaOS, String> col_NomeDependencia;
    @FXML
    private TableColumn<TabelaOS, String> col_ProblemaApresentado;
    @FXML
    private TableColumn<TabelaOS, Date> col_DataHoraAbertura;
    @FXML
    private TableColumn<TabelaOS, String> col_UF;
    @FXML
    private TableColumn<TabelaOS, String> col_NumeroContrato;
    @FXML
    private TableColumn<TabelaOS, String> col_Fornecedor;
    @FXML
    private TableColumn<TabelaOS, String> col_NrSOL;
    @FXML
    private TableColumn<TabelaOS, String> col_TipoManutencao;
       @FXML
    private TableView<TabelaOS> tb_os;
    @FXML
    private TableColumn<TabelaOS, String> col_os;

    ConsultasSQL sql = new ConsultasSQL();
    utils util = new utils();
    @FXML
    private JFXButton btn_excel;
    
    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           String  matricula = System.getProperty("user.name").toUpperCase();
          //String matricula = "Sancho";
            String estados = sql.pegaEstadosFunci(matricula);
            atualizaTabela(estados);
            // TODO
        } catch (Throwable ex) {
            Logger.getLogger(OSAbertasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void atualizaTabela(String estados) throws Throwable{
        
        
                   List<TabelaOS> populaTabela = sql.osAbertas(estados);
            if (!populaTabela.isEmpty()) {

                ObservableList<TabelaOS> observableListOS = FXCollections.observableList(populaTabela);

                col_os.setCellValueFactory(new PropertyValueFactory<>("OS"));
                col_NumeroBem.setCellValueFactory(new PropertyValueFactory<>("NumeroBem"));
                col_Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
                col_Prefixo.setCellValueFactory(new PropertyValueFactory<>("Prefixo"));
                col_SB.setCellValueFactory(new PropertyValueFactory<>("SB"));
                col_NomeDependencia.setCellValueFactory(new PropertyValueFactory<>("NomeDependencia"));
                col_ProblemaApresentado.setCellValueFactory(new PropertyValueFactory<>("ProblemaApresentado"));
                col_DataHoraAbertura.setCellValueFactory(new PropertyValueFactory<>("DataHoraAbertura"));
                col_UF.setCellValueFactory(new PropertyValueFactory<>("UF"));
                col_NumeroContrato.setCellValueFactory(new PropertyValueFactory<>("NumeroContrato"));
                col_Fornecedor.setCellValueFactory(new PropertyValueFactory<>("Fornecedor"));
                col_NrSOL.setCellValueFactory(new PropertyValueFactory<>("NrSOL"));
                col_TipoManutencao.setCellValueFactory(new PropertyValueFactory<>("TipoManutencao"));

                tb_os.setItems(observableListOS);

            } else {
            Platform.runLater(() -> {

                util.alertaValidacao("Não foram localizados no registro");

            });

            }
    }

    @FXML
    private void GeraExcel(ActionEvent event) {
        GeraExcel gerar = new GeraExcel();
        List<TabelaOS> listaExcel = tb_os.getItems();
        gerar.geraExcelPendentes(listaExcel);
    }
    
}
