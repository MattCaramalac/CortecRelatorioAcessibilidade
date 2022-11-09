package com.mpms.relatorioacessibilidadecortec.report;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUpdate {

    static final String INSPECT_OBJ = "a seguir:";
    static final String IRREGULARITIES = "estrutura física/conservação da construção:";

    public File path;
    public String fName;
    public String fileName;
    static FileOutputStream outStream;
    static JsonCreation jCreate;
    static List<String> blockContent;
    static XWPFDocument doc;
    static final Logger LOGGER = LogManager.getLogger(TextUpdate.class);

    public boolean docFiller(HashMap<String, String> variable, Uri uri, Context ctx) {
        try {
            doc = new XWPFDocument(ctx.getAssets().open("template.docx"));
            changeParagraphs(doc.getParagraphs(), variable);
            alterTable(doc.getTablesIterator(), variable);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                outStream = new FileOutputStream(fileName);
            } else {
                ParcelFileDescriptor parFile = ctx.getContentResolver().openFileDescriptor(uri, "w");
                outStream = new FileOutputStream(parFile.getFileDescriptor());
            }
            doc.write(outStream);
            doc.close();
            outStream.close();
        } catch (IOException | OpenXML4JRuntimeException | XmlException e) {
            LOGGER.error(e);
        }
        return true;
    }

    public void setJsonCreation(JsonCreation jCreate, List<String> blockContent) {
        TextUpdate.jCreate = jCreate;
        TextUpdate.blockContent = blockContent;
    }

    public String newFileName() {
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!path.exists()) {
            path.mkdirs();
        }
        fName = "Report";
        File file = new File(path + "/" + fName + ".docx");
        int fileNo = 0;
        String newName = "";

        if (file.exists() && !file.isDirectory()) {
            while (file.exists()) {
                fileNo++;
                fName = "Report" + fileNo;
                file = new File(path + "/" + fName + ".docx");
                newName = path + "/" + fName + ".docx";
                fileName = newName;
            }
        } else if (!file.exists()) {
            newName = path + "/" + fName + ".docx";
            fileName = newName;
        }

        return newName;
    }

    private static void changeParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> variable) throws XmlException {
        int pos = 0, irr = 0;
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
                    if (text.contains(INSPECT_OBJ))
                        pos = paragraphs.indexOf(paragraph);
                }
                if (text.contains(IRREGULARITIES))
                    irr = paragraphs.indexOf(paragraph);
            }
        }
        if (pos > 0)
            CreateWordList.listCreator(doc, paragraphs.get(pos + 1), blockContent);
        if (irr > 0) {
            AmbientAnalysis analysis = new AmbientAnalysis(doc, paragraphs.get(irr + 1), jCreate);
            analysis.writeTesting();
        }

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
                else {
//                    TODO - Fazer a quebra de linha eventualmente
//                    if (mat != null && mat.equals("techName")) {
////                        Pattern sPat = Pattern.compile("(,)|(\\s*e\\s+)");
////                        Matcher sMat = sPat.matcher(variable);
//                        variable = variable.replaceAll("(,)|(\\s*e\\s+)", "\\r\\n");
//                    }
                    builder.append(variable);
                }
                i = matcher.end();
            }
            builder.append(text.substring(i));
            return builder.toString();
        } else
            return text;
    }

    private static void alterTable(Iterator<XWPFTable> itTable, Map<String, String> variaveis) throws XmlException {
        while (itTable.hasNext()) {
            XWPFTable table = itTable.next();
            extractLines(table, variaveis);
        }
    }

    private static void extractLines(XWPFTable table, Map<String, String> variaveis) throws XmlException {
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
