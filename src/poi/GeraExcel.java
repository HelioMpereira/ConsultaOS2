/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.application.Platform;
import model.TabelaOS;
import model.utils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author User
 */
public class GeraExcel {

    private static String fileName;

    HSSFWorkbook workbook = new HSSFWorkbook();

    utils util = new utils();

    public void geraExcelPendentes(List<TabelaOS> listaExcel) {
// String  matricula = System.getProperty("user.name").toUpperCase();
        //Criação da planilha
        Workbook workbook = new HSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
//        Sheet sheet = workbook.createSheet("GSV Pendentes");
        HSSFSheet firstSheet = (HSSFSheet) workbook.createSheet("OS Abertas");
        
        CellStyle backgroundStyle = workbook.createCellStyle();
        
        
        //Estilo do Background
//        backgroundStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());  
//        backgroundStyle.setFillPattern(FillPatternType.); 
//        backgroundStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());

        //Estilo da fonte
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 13);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        

        //Estilo da célula
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        //Criação da Linha de cabeçalho
        Row headerRow = firstSheet.createRow(0);
        org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(0);
        cell.setCellValue("OS");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(1);
        cell.setCellValue("AnoOS");
        cell.setCellStyle(headerCellStyle);
//        cell.setCellStyle(backgroundStyle);

        cell = headerRow.createCell(2);
        cell.setCellValue("NumeroOS");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(3);
        cell.setCellValue("NumeroBem");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(4);
        cell.setCellValue("Status");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(5);
        cell.setCellValue("Prefixo");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(6);
        cell.setCellValue("SB");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(7);
        cell.setCellValue("NomeDependencia");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(8);
        cell.setCellValue("ProblemaApresentado");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(9);
        cell.setCellValue("DataHoraAbertura");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(10);
        cell.setCellValue("UF");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(11);
        cell.setCellValue("NumeroContrato");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(12);
        cell.setCellValue("Fornecedor");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(13);
        cell.setCellValue("NrSOL");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(14);
        cell.setCellValue("TipoManutencao");
        cell.setCellStyle(headerCellStyle);

        int i = 1;
        for (TabelaOS t : listaExcel) {
            HSSFRow row = firstSheet.createRow(i);

            row.createCell(0).setCellValue(t.getOS());
            row.createCell(1).setCellValue(t.getAnoOS());
            row.createCell(2).setCellValue(t.getNumeroOS());
            row.createCell(3).setCellValue(t.getNumeroBem());
            row.createCell(4).setCellValue(t.getStatus());
            row.createCell(5).setCellValue(t.getPrefixo());
            row.createCell(6).setCellValue(t.getSB());
            row.createCell(7).setCellValue(t.getNomeDependencia());
            row.createCell(8).setCellValue(t.getProblemaApresentado());
            
            String dataAbertura = new SimpleDateFormat("dd-MM-yyyy").format(t.getDataHoraAbertura());
            row.createCell(9).setCellValue(dataAbertura);
            row.createCell(10).setCellValue(t.getUF());
            row.createCell(11).setCellValue(t.getNumeroContrato());
            row.createCell(12).setCellValue(t.getFornecedor());
            row.createCell(13).setCellValue(t.getNrSOL());
            row.createCell(14).setCellValue(t.getTipoManutencao());

            i++;

        }

        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String date = new SimpleDateFormat("dd-MM-yyyy").format(timestamp.getTime());

            // d fileName = "C:\\Users\\"+matricula+"\\Desktop\\Gsv Pendentes " + date + ".xls";
            fileName = "‪C:/Users/Public/os.xls";
//            FileOutputStream out = new FileOutputStream(fileName);
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\OS abertas " + date + ".xls"));
            workbook.write(out);
            out.close();
            System.out.println("Arquivo Excel criado com sucesso!");

            Platform.runLater(() -> {

                util.alertaGeralInformacao("Atenção", "Arquivo Excel gerado com sucesso!", "Verifique a área de trabalho!");

            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }
    }

}
