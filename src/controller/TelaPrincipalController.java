/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.utils;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import CurrencyField.CurrencyField;
import dao.ConsultasSQL;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.TabelaOS;
import model.TextFieldFormatter;
import poi.GeraExcel;
import subsidioCartao.MainApp;

/**
 * FXML Controller class
 *
 * @author f3295813
 */
public class TelaPrincipalController implements Initializable {

    Thread t1;
    Thread t2;
    Thread t3;

    utils util = new utils();
    
      
    //=====================================================================================================
    @FXML
    private AnchorPane AnchorPanePrincipal;
   
    @FXML
    private TextField txtChave;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane AP_ParcelamentoFatura;

    @FXML
    private Tab tab_ParcelamentoFatura;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabInformacoes;
   
   
    @FXML
    private Tab tab_BloqueioRestricao;
       
    AtribuicaoController atribuicao = new AtribuicaoController();
    @FXML
    private TextField txt_dtinicio;
    @FXML
    private TextField txt_dtfim;
    @FXML
    private JFXButton btn_atualizar;
    @FXML
    private TableColumn<TabelaOS, Integer> col_AnoOS;
    @FXML
    private TableColumn<TabelaOS, Integer> col_NumeroOS;
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
    private JFXButton btn_excel;
    @FXML
    private TableView<TabelaOS> tb_os;
    @FXML
    private TableColumn<TabelaOS, String> col_os;
    MainApp mainApp;
    String matricula;
    @FXML
    private AnchorPane AP_OS;
    
    OSAbertasController os = new OSAbertasController();
     ConsultasSQL sql = new ConsultasSQL();

    public TelaPrincipalController() throws PropertyVetoException {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            matricula = System.getProperty("user.name").toUpperCase();// pega matrícula
            txtChave.setText(matricula);
            Image imagem = new Image("/views/imagens/logo.png");
            imageView.setImage(imagem);
            
            int p = sql.permissao(matricula);
            
            if(p !=1){
                
                   
            tabInformacoes.setDisable(true);
             tab_ParcelamentoFatura.setDisable(true);
         
                
            }
            
         
            
            CurrencyField cur = new CurrencyField();
            
            ScrollPane sp = new ScrollPane();
            sp.setContent(sp);
            
            
// Usando esta property você pode ver as mudanças no valor do textfield
cur.amountProperty().addListener(new ChangeListener<Number>() {
    
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.println(newValue.doubleValue());
    }
});
        } catch (Throwable ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
   

    @FXML
    private void selecionaAtribuicao(Event event) {
        
        String path = "/view/AtribuicaoEstados.fxml";
        atribuicao =  (AtribuicaoController) mainApp.showCenterAnchorPaneWithReturn(path, atribuicao, AP_ParcelamentoFatura);


    }
    
        @FXML
    private void osAbertas(Event event) {
        
          String path = "/view/OSAbertas.fxml";
        os =   (OSAbertasController) mainApp.showCenterAnchorPaneWithReturn(path, os, AP_OS);

    }

        @FXML
    private void atualizarTabela(ActionEvent event) throws Throwable {
       
        String UF = "";

        if (txt_dtinicio.getText().length() != 0 && txt_dtfim.getText().length() != 0) {

            List<TabelaOS> populaTabela = sql.atualizarTabela(txt_dtinicio.getText(), txt_dtfim.getText());

            if (!populaTabela.isEmpty()) {

                ObservableList<TabelaOS> observableListOS = FXCollections.observableList(populaTabela);

                col_os.setCellValueFactory(new PropertyValueFactory<>("OS"));
                col_AnoOS.setCellValueFactory(new PropertyValueFactory<>("AnoOS"));
                col_NumeroOS.setCellValueFactory(new PropertyValueFactory<>("NumeroOS"));
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
        } else {
            Platform.runLater(() -> {

                util.alertaValidacao("Preencher as datas no formato DDMMAAAA");

            });

        }
    }

    @FXML
    private void GeraExcel(ActionEvent event) {
        
        GeraExcel gerar = new GeraExcel();
        List<TabelaOS> listaExcel = tb_os.getItems();
        gerar.geraExcelPendentes(listaExcel);
    }
    
     @FXML
    private void inputDataKeyTypedDataInicial(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##-##-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txt_dtinicio);
        tff.formatter();
    }

    @FXML
    private void inputDataKeyTypedDataFinal(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##-##-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txt_dtfim);
        tff.formatter();
    }




}
