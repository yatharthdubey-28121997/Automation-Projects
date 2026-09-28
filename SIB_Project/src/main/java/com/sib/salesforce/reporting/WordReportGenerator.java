package com.sib.salesforce.reporting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class WordReportGenerator {

    public static void generateReport(
            String project,
            String environment,
            List<ReportingModel> items,
            String filePath) throws Exception {

        XWPFDocument doc = new XWPFDocument();

        addTitle(doc);

        addProjectInfo(doc, project, environment);

        addExecutionSummary(doc, items);

        addPieChart(doc, items); // MUST remain before page break

        addPageBreak(doc);

        addResultTable(doc, items);

        FileOutputStream out = new FileOutputStream(filePath);
        doc.write(out);
        out.close();
    }

    private static void addTitle(XWPFDocument doc) {

        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        p.setSpacingAfter(400);

        XWPFRun run = p.createRun();
        run.setBold(true);
        run.setFontFamily("Calibri");
        run.setFontSize(30);
        run.setText("AUTOMATION TEST EXECUTION");

        XWPFParagraph sub = doc.createParagraph();
        sub.setAlignment(ParagraphAlignment.CENTER);
        sub.setSpacingAfter(300);

        XWPFRun run2 = sub.createRun();
        run2.setBold(true);
        run2.setFontSize(18);
        run2.setText("Executive Test Summary");
    }

    private static void addProjectInfo(XWPFDocument doc, String project, String env) {

        XWPFTable table = doc.createTable(2,3);
        setTableWidth(table);

        table.getRow(0).setHeight(400);
        table.getRow(1).setHeight(400);

        createHeader(table.getRow(0).getCell(0),"Project");
        createHeader(table.getRow(0).getCell(1),"Environment");
        createHeader(table.getRow(0).getCell(2),"Execution Date");

        setCell(table.getRow(1).getCell(0),project);
        setCell(table.getRow(1).getCell(1),env);
        setCell(table.getRow(1).getCell(2), LocalDate.now().toString());
    }

    private static void addExecutionSummary(XWPFDocument doc,List<ReportingModel> items){

        int pass=0,fail=0,notExecuted=0;

        for(ReportingModel t:items){
            switch(t.getStatus()){
                case PASS: pass++; break;
                case FAIL: fail++; break;
                case NOT_EXECUTED: notExecuted++; break;
            }
        }

        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        title.setSpacingBefore(300);

        XWPFRun run = title.createRun();
        run.setBold(true);
        run.setFontSize(18);
        run.setText("Execution Summary");

        XWPFTable table = doc.createTable(2,4);
        setTableWidth(table);

        table.getRow(0).setHeight(400);
        table.getRow(1).setHeight(400);

        createHeader(table.getRow(0).getCell(0),"Total Tests");
        createHeader(table.getRow(0).getCell(1),"Passed");
        createHeader(table.getRow(0).getCell(2),"Failed");
        createHeader(table.getRow(0).getCell(3),"Not Executed");

        setColoredCell(table.getRow(1).getCell(0),String.valueOf(items.size()),"D9E1F2");
        setColoredCell(table.getRow(1).getCell(1),String.valueOf(pass),"C6EFCE");
        setColoredCell(table.getRow(1).getCell(2),String.valueOf(fail),"FFC7CE");
        setColoredCell(table.getRow(1).getCell(3),String.valueOf(notExecuted),"FCE4D6");
    }

    private static void addPieChart(XWPFDocument doc,List<ReportingModel> items) throws Exception {

        int pass=0,fail=0,notExecuted=0;

        for(ReportingModel t:items){
            switch(t.getStatus()){
                case PASS: pass++; break;
                case FAIL: fail++; break;
                case NOT_EXECUTED: notExecuted++; break;
            }
        }

        BufferedImage chart = createPieChart(pass,fail,notExecuted);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(chart,"png",baos);

        XWPFParagraph chartTitle = doc.createParagraph();
        chartTitle.setAlignment(ParagraphAlignment.CENTER);
        chartTitle.setSpacingBefore(300);

        XWPFRun titleRun = chartTitle.createRun();
        titleRun.setBold(true);
        titleRun.setFontSize(18);
        titleRun.setText("Execution Distribution");

        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = p.createRun();

        run.addPicture(
                new ByteArrayInputStream(baos.toByteArray()),
                Document.PICTURE_TYPE_PNG,
                "chart.png",
                Units.toEMU(520),
                Units.toEMU(320)
        );
    }

    private static BufferedImage createPieChart(int pass,int fail,int notExec){

        int width = 900;
        int height = 500;

        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);

        int total = pass + fail + notExec;

        int startAngle = 0;

        int passAngle = (int)(360.0 * pass / total);
        int failAngle = (int)(360.0 * fail / total);
        int notExecAngle = 360 - passAngle - failAngle;

        int size = 260;

        g.setColor(new Color(46,204,113));
        g.fillArc(80,80,size,size,startAngle,passAngle);

        startAngle += passAngle;

        g.setColor(new Color(231,76,60));
        g.fillArc(80,80,size,size,startAngle,failAngle);

        startAngle += failAngle;

        g.setColor(new Color(241,196,15));
        g.fillArc(80,80,size,size,startAngle,notExecAngle);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri",Font.BOLD,18));

        g.drawString("PASS : "+pass,420,180);
        g.drawString("FAIL : "+fail,420,220);
        g.drawString("NOT EXECUTED : "+notExec,420,260);

        g.dispose();

        return img;
    }

    private static void addPageBreak(XWPFDocument doc){

        XWPFParagraph breakPara = doc.createParagraph();
        XWPFRun run = breakPara.createRun();
        run.addBreak(BreakType.PAGE);
    }

    private static void addResultTable(XWPFDocument doc,List<ReportingModel> items){

        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = p.createRun();
        run.setBold(true);
        run.setFontSize(18);
        run.setText("Detailed Test Results");

        XWPFTable table = doc.createTable(1,4);
        setTableWidth(table);

        createHeader(table.getRow(0).getCell(0),"S.No");
        createHeader(table.getRow(0).getCell(1),"Test Item");
        createHeader(table.getRow(0).getCell(2),"Status");
        createHeader(table.getRow(0).getCell(3),"Comments");

        int i=1;

        for(ReportingModel item:items){

            XWPFTableRow row = table.createRow();
            row.setHeight(400);

            setCell(row.getCell(0),String.valueOf(i++));
            setCell(row.getCell(1),item.getItem());
            setStatusCell(row.getCell(2),item.getStatus().name());
            setCell(row.getCell(3),item.getComments());
        }
    }

    private static void setTableWidth(XWPFTable table){

        CTTblWidth width = table.getCTTbl().getTblPr().addNewTblW();
        width.setType(STTblWidth.PCT);
        width.setW(BigInteger.valueOf(5000));
    }

    private static void createHeader(XWPFTableCell cell,String text){

        cell.setColor("305496");

        XWPFParagraph p = cell.getParagraphs().get(0);
        p.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = p.createRun();
        run.setBold(true);
        run.setColor("FFFFFF");
        run.setText(text);
    }

    private static void setCell(XWPFTableCell cell,String text){

        XWPFParagraph p = cell.getParagraphs().get(0);
        p.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun run = p.createRun();
        run.setText(text);
    }

    private static void setColoredCell(XWPFTableCell cell,String text,String color){

        cell.setColor(color);

        XWPFParagraph p = cell.getParagraphs().get(0);
        p.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun run = p.createRun();
        run.setBold(true);
        run.setText(text);
    }

    private static void setStatusCell(XWPFTableCell cell,String status){

        String color="FFFFFF";

        if(status.equals("PASS")) color="C6EFCE";
        if(status.equals("FAIL")) color="FFC7CE";
        if(status.equals("NOT_EXECUTED")) color="FCE4D6";

        setColoredCell(cell,status,color);
    }
}