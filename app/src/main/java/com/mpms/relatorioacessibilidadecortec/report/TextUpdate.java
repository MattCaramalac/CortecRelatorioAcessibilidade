package com.mpms.relatorioacessibilidadecortec.report;

import android.content.Context;
import android.os.Environment;
import android.service.controls.Control;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUpdate {

    public static String fileName = "";


    public TextUpdate getInstance(Context ctx) {
        return new TextUpdate();
    }

    public static String exchangeVariables(String text, Map<String, String> exchange) {
        if (text.contains("$")) {
            Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
            Matcher matcher = pattern.matcher(text);
            StringBuilder builder = new StringBuilder();
            int i = 0;
            while (matcher.find()) {
                String mat = matcher.group(1);
                String variable = exchange.get(mat);

                builder.append(text, i, matcher.start());
                if (variable == null)
                    builder.append("");
                else
                    builder.append(variable);
                i = matcher.end();
            }
            builder.append(text.substring(i));
            return builder.toString();
        } else
            return text;
    }

    public static void reportGenerator(HashMap<String, String> variable, Context ctx) throws OpenXML4JException, IOException {
        try {
            fileName = newFileName();
            XWPFDocument doc = new XWPFDocument(ctx.getAssets().open("template.docx"));
            changeParagraphs(doc.getParagraphs(), variable);
            alterTable(doc.getTablesIterator(), variable);
            FileOutputStream outStream = new FileOutputStream(fileName);
            doc.write(outStream);
            doc.close();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String newFileName() throws IOException {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path + "report.docx");
        int fileNo = 0;
        String newName = "";

        if (file.exists() && !file.isDirectory()) {
            while (file.exists()) {
                fileNo++;
                file = new File(path + "report" + fileNo + ".docx");
                newName = path + "report" + fileNo + ".docx";
            }
        } else if (!file.exists()) {
            newName = path + "report.docx";
        }
        return newName;
    }
    private static void changeParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> variable) {
        for (XWPFParagraph paragraph : paragraphs) {
            if (!paragraph.getRuns().isEmpty()) {
                String text = "";
                for (XWPFRun run : paragraph.getRuns()) {
                    if (run.getText(run.getTextPosition()) != null) {
                        text = text.concat(run.getText(run.getTextPosition()));
                    }
                }
                if (text.contains("${")) {
                    String newText = exchangeVariables(text, variable);
                    paragraph.getRuns().forEach((run) -> run.setText(null, 0));
                    paragraph.getRuns().get(0).setText(newText);
                }
            }
        }
    }

    private static void alterTable(Iterator<XWPFTable> itTable, Map<String, String> variaveis) {
        while (itTable.hasNext()) {
            XWPFTable table = itTable.next();
            extractLines(table, variaveis);
        }
    }

    private static void extractLines(XWPFTable table, Map<String, String> variaveis) {
        int rcount = table.getNumberOfRows();
        for (int j = 0; j < rcount; j++) {
            XWPFTableRow row = table.getRow(j);
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                changeParagraphs(cell.getParagraphs(), variaveis);
            }
        }
    }
}
