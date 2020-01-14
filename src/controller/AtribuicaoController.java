/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import dao.ConsultasSQL;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.TabelaFunciAtribuicao;
import model.TabelaOS;
import model.utils;

/**
 * FXML Controller class
 *
 * @author fla_a
 */
public class AtribuicaoController extends AbstractController implements Initializable {

    @FXML
    private AnchorPane AP_Parcelamento;
    @FXML
    private JFXCheckBox acre;
    @FXML
    private JFXCheckBox alagoas;
    @FXML
    private JFXCheckBox amapa;
    @FXML
    private JFXCheckBox Amazonas;
    @FXML
    private JFXCheckBox Bahia;
    @FXML
    private JFXCheckBox Ceará;
    @FXML
    private JFXCheckBox DF;
    @FXML
    private JFXCheckBox EspiitoSanto;
    @FXML
    private JFXCheckBox Goias;
    @FXML
    private JFXCheckBox maranhao;
    @FXML
    private JFXCheckBox MatoGrosso;
    @FXML
    private JFXCheckBox mtSul;
    @FXML
    private JFXCheckBox minasGerais;
    @FXML
    private JFXCheckBox para;
    @FXML
    private JFXCheckBox roraima;
    @FXML
    private JFXCheckBox rondonia;
    @FXML
    private JFXCheckBox rgSul;
    @FXML
    private JFXCheckBox rgNorte;
    @FXML
    private JFXCheckBox rj;
    @FXML
    private JFXCheckBox piaui;
    @FXML
    private JFXCheckBox pernambuco;
    @FXML
    private JFXCheckBox paraiba;
    @FXML
    private JFXCheckBox santaCatarina;
    @FXML
    private JFXCheckBox parana;
    @FXML
    private JFXCheckBox saoPaulo;
    @FXML
    private JFXCheckBox sergipe;
    @FXML
    private JFXCheckBox tocantins;
    @FXML
    private JFXComboBox<String> cb_funci;
    @FXML
    private TableView<TabelaFunciAtribuicao> tb_estados;
    @FXML
    private TableColumn<TabelaFunciAtribuicao, String> col_funci;
    @FXML
    private TableColumn<TabelaFunciAtribuicao, String> col_estado;
    @FXML
    private JFXButton btn_atualizar;

    utils util = new utils();
    ConsultasSQL sql = new ConsultasSQL();
    @FXML
    private TableColumn<TabelaFunciAtribuicao, Boolean> col_selected;
    @FXML
    private JFXButton btn_excluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<String> funci = sql.carregaFunci();
            //  ObservableList<String> observableListFunci = FXCollections.observableList(funci);
            cb_funci.getItems().addAll(funci);

            atualizaTabela();

            // TODO
        } catch (Throwable ex) {
            Logger.getLogger(AtribuicaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
//         tb_estados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//               deletaRegistro(newValue);
//            } catch (Throwable ex) {
//                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//        
    }

    @FXML
    private void atualizarAtribuicao(ActionEvent event) throws Throwable {

        List<TabelaFunciAtribuicao> listaUpload = new ArrayList<>();

        String funciSelCb = cb_funci.getValue();

        int pk = sql.pegaPkFunci(funciSelCb);

        String funciSel = String.valueOf(pk);

        if (funciSel != null) {

            if (acre.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("1");
                listaUpload.add(t);

            }
            if (alagoas.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("2");
                listaUpload.add(t);
            }
            if (amapa.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("4");
                listaUpload.add(t);

            }
            if (Amazonas.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("3");
                listaUpload.add(t);

            }
            if (Bahia.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("5");
                listaUpload.add(t);

            }
            if (Ceará.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("6");
                listaUpload.add(t);
            }
            if (DF.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("7");
                listaUpload.add(t);
            }
            if (EspiitoSanto.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("8");
                listaUpload.add(t);

            }
            if (Goias.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("9");
                listaUpload.add(t);

            }
            if (maranhao.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("10");
                listaUpload.add(t);
            }
            if (MatoGrosso.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("13");
                listaUpload.add(t);

            }
            if (mtSul.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("12");
                listaUpload.add(t);
            }
            if (minasGerais.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("11");
                listaUpload.add(t);
            }
            if (para.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("14");
                listaUpload.add(t);
            }
            if (roraima.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("22");
                listaUpload.add(t);

            }
            if (rondonia.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("21");
                listaUpload.add(t);
            }
            if (rgSul.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("23");
                listaUpload.add(t);

            }
            if (rgNorte.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("20");
                listaUpload.add(t);
            }
            if (rj.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("19");
                listaUpload.add(t);
            }
            if (piaui.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("17");
                listaUpload.add(t);

            }
            if (pernambuco.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("16");
                listaUpload.add(t);

            }
            if (paraiba.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("15");
                listaUpload.add(t);
            }
            if (santaCatarina.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("24");
                listaUpload.add(t);
            }
            if (parana.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("18");
                listaUpload.add(t);
            }
            if (saoPaulo.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("26");
                listaUpload.add(t);

            }
            if (sergipe.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("25");
                listaUpload.add(t);

            }
            if (tocantins.isSelected()) {
                TabelaFunciAtribuicao t = new TabelaFunciAtribuicao();
                t.setFunci(funciSel);
                t.setEstado("27");
                listaUpload.add(t);
            }

        } else {

            Platform.runLater(() -> {

                util.alertaValidacao("Gentileza selecionar um funcionário!");

            });

        }

        if (sql.updateEstado(listaUpload)) {

            Platform.runLater(() -> {

                util.alertaGeralInformacao("Feito!!", "Estados atualizados com sucesso!", "Bom trabalho Gisele!!");

            });

        } else {

            Platform.runLater(() -> {

                util.alertaGeral("Ops", "Alguma coisa deu errado!", "Estado não atualizado =(");

            });

        }
        atualizaTabela();
        limpaSB();
    }

    public void atualizaTabela() throws Throwable {

        List<TabelaFunciAtribuicao> populaTabela = sql.populaTabelaEstado();
          ObservableList<TabelaFunciAtribuicao> observableList = FXCollections.observableList(populaTabela);

        if (!populaTabela.isEmpty()) {

          

            col_funci.setCellValueFactory(new PropertyValueFactory<>("funci"));
            col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
              col_selected.setCellValueFactory((CellDataFeatures<TabelaFunciAtribuicao, Boolean> param) -> {
                    TabelaFunciAtribuicao dados = param.getValue();
                    SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(dados.isSelected());
                    booleanProp.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                        dados.setSelected(newValue);
                    });
                    return booleanProp;
                });
                col_selected.setCellFactory((TableColumn<TabelaFunciAtribuicao, Boolean> p) -> {
                    CheckBoxTableCell<TabelaFunciAtribuicao, Boolean> cell = new CheckBoxTableCell<>();
                    return cell;
                });

         

        }
        
          tb_estados.setItems(observableList); 

    }

    public void limpaSB() {

        acre.setSelected(false);

        alagoas.setSelected(false);
        amapa.setSelected(false);
        Amazonas.setSelected(false);
        Bahia.setSelected(false);
        Ceará.setSelected(false);
        DF.setSelected(false);
        EspiitoSanto.setSelected(false);
        Goias.setSelected(false);
        maranhao.setSelected(false);
        MatoGrosso.setSelected(false);
        mtSul.setSelected(false);
        minasGerais.setSelected(false);
        para.setSelected(false);
        roraima.setSelected(false);
        rondonia.setSelected(false);
        rgSul.setSelected(false);
        rgNorte.setSelected(false);
        rj.setSelected(false);
        piaui.setSelected(false);
        pernambuco.setSelected(false);
        paraiba.setSelected(false);
        santaCatarina.setSelected(false);
        parana.setSelected(false);
        saoPaulo.setSelected(false);
        sergipe.setSelected(false);
        tocantins.setSelected(false);

    }

   
    @FXML
    private void excluir(ActionEvent event) throws Throwable {
        
        List<TabelaFunciAtribuicao> listaFunciTab = tb_estados.getItems();
          List<Integer> listaFunciExcluir = new ArrayList<>();
        
        for(TabelaFunciAtribuicao t : listaFunciTab){
            
            if(t.isSelected()){
                int f = t.getCd();
                listaFunciExcluir.add(f);
                
            }
            
        }
        
                     
   
      
     if(sql.deletaRegistro(listaFunciExcluir)){
            Platform.runLater(() -> {

                util.alertaGeralInformacao("Feito!", "Registro deletado com sucesso!", null);

            });
         
        
    }else{
         
            Platform.runLater(() -> {

                util.alertaGeral("Ops", "Alguma coisa deu errado!", "Estado não atualizado =(");

            });
     }
     
   
   
atualizaTabela();
    }

}
